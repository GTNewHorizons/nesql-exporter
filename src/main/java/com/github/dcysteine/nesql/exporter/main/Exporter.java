package com.github.dcysteine.nesql.exporter.main;

import codechicken.nei.ItemList;
import com.github.dcysteine.nesql.exporter.main.config.ConfigOptions;
import com.github.dcysteine.nesql.exporter.plugin.ExporterState;
import com.github.dcysteine.nesql.exporter.plugin.PluginExporter;
import com.github.dcysteine.nesql.exporter.registry.PluginRegistry;
import com.github.dcysteine.nesql.exporter.render.RenderDispatcher;
import com.github.dcysteine.nesql.exporter.render.Renderer;
import com.github.dcysteine.nesql.sql.Metadata;
import com.github.dcysteine.nesql.sql.Plugin;
import com.google.common.collect.ImmutableMap;
import cpw.mods.fml.relauncher.FMLInjectionData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.io.FileUtils;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

/** Exports recipes and other data to a file. */
public final class Exporter {
    private static final String REPOSITORY_PATH_FORMAT_STRING = "nesql" + File.separator + "%s";
    private static final String DATABASE_FILE_PATH = "nesql-db";
    private static final String IMAGE_ZIP_PATH = "image.zip";

    /** If true, existing repositories will be deleted and overwritten. */
    private final boolean overwrite;
    private final String repositoryName;
    private final File repositoryDirectory;
    private final File databaseFile;
    private final File imageZipFile;

    public Exporter(boolean overwrite) {
        this(overwrite, ConfigOptions.REPOSITORY_NAME.get());
    }

    public Exporter(boolean overwrite, String repositoryName) {
        this.overwrite = overwrite;
        this.repositoryName = repositoryName;
        this.repositoryDirectory =
                new File(
                        (File) FMLInjectionData.data()[6],
                        String.format(REPOSITORY_PATH_FORMAT_STRING, repositoryName));
        this.databaseFile = new File(repositoryDirectory, DATABASE_FILE_PATH);
        this.imageZipFile = new File(repositoryDirectory, IMAGE_ZIP_PATH);
    }

    /**
     * Wrapper for {@link #export()} which will report exceptions to chat.
     *
     * <p>This is needed because exceptions thrown within threads only appear in logs.
     */
    public void exportReportException() {
        // Bit of a hack, but if we're exporting upon client connect,
        // then we need to wait for the player entity to become available.
        while (Minecraft.getMinecraft().thePlayer == null) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException wakeUp) {}
        }

        if (ConfigOptions.ENABLED_PLUGINS.get().contains(Plugin.NEI.getName())
                && ItemList.items.isEmpty()) {
            Logger.chatMessage(
                    EnumChatFormatting.RED + "NEI item list is empty! Please load it, and retry.");
            return;
        }

        try {
            export();
        } catch (Exception e) {
            Logger.chatMessage(
                    EnumChatFormatting.RED
                            + "Something went wrong during export! Please check your logs.");
            throw e;
        } finally {
            // If we crash, stop rendering things.
            RenderDispatcher.INSTANCE.setRendererState(RenderDispatcher.RendererState.ERROR);
        }
    }

    private void export() {
        Logger.chatMessage(EnumChatFormatting.AQUA + "Exporting data to:");
        Logger.chatMessage(repositoryDirectory.getAbsolutePath());

        if (repositoryDirectory.exists()) {
            if (overwrite) {
                Logger.chatMessage(
                        EnumChatFormatting.YELLOW + String.format(
                                "Repository \"%s\" already exists; deleting...", repositoryName));
                try {
                    FileUtils.deleteDirectory(repositoryDirectory);
                } catch (IOException e) {
                    Logger.chatMessage(
                            EnumChatFormatting.RED + String.format(
                                    "Could not delete repository \"%s\"!", repositoryName));
                    throw new RuntimeException(e);
                }
            } else {
                Logger.chatMessage(
                        EnumChatFormatting.RED + String.format(
                                "Cannot create repository \"%s\"; it already exists!", repositoryName));
                return;
            }
        }
        if (!repositoryDirectory.mkdirs()) {
            Logger.chatMessage(
                    EnumChatFormatting.RED
                            + String.format("Failed to create repository \"%s\"!", repositoryName));
            return;
        }

        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.connection.username", ConfigOptions.DATABASE_USER.get());
        properties.put("hibernate.connection.password", ConfigOptions.DATABASE_PASSWORD.get());

        if (ConfigOptions.USE_POSTGRESQL.get()) {
            properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
            properties.put(
                    "hibernate.connection.url",
                    String.format(
                            "jdbc:postgresql://localhost:%d/%s",
                            ConfigOptions.POSTGRESQL_PORT.get(), repositoryName));
        } else {
            // Append this to the path if we need cached tables.
            // Note: this seems to 3x or 4x the time needed to export.
            // This should drastically decrease NESQL server start-up time, at the cost of slightly
            // increasing query time.
            //+ ";hsqldb.default_table_type=CACHED"
            //
            // Append this if we need to use lobs (this will set min lob size to 1KB).
            //+ ";hsqldb.lob_compressed=true;hsqldb.lob_file_scale=1"
            properties.put(
                    "hibernate.connection.url",
                    "jdbc:hsqldb:file:" + databaseFile.getAbsolutePath());
        }

        EntityManagerFactory entityManagerFactory =
                new HibernatePersistenceProvider()
                        .createEntityManagerFactory("NESQL", properties);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        boolean renderingImages =
                ConfigOptions.RENDER_ICONS.get() || ConfigOptions.RENDER_MOBS.get();
        FileSystem imageZipFileSystem = null;
        if (renderingImages) {
            Logger.chatMessage(EnumChatFormatting.AQUA + "Initializing renderer.");

            URI uri = URI.create("jar:" + imageZipFile.toURI());
            Map<String, String> env = ImmutableMap.of("create", "true");
            try {
                imageZipFileSystem = FileSystems.newFileSystem(uri, env);
            } catch (IOException e) {
                Logger.chatMessage(
                        EnumChatFormatting.RED
                                + String.format(
                                        "Failed to create image zip file:\n%s",
                                        imageZipFile.getPath()));
                e.printStackTrace();
                return;
            }

            Renderer.INSTANCE.preInitialize(imageZipFileSystem);
            RenderDispatcher.INSTANCE.setRendererState(RenderDispatcher.RendererState.INITIALIZING);
        }

        Logger.chatMessage(EnumChatFormatting.AQUA + "Initializing plugins.");
        ExporterState exporterState = new ExporterState(entityManager);
        PluginRegistry registry = new PluginRegistry();
        Map<Plugin, PluginExporter> activePlugins = registry.initialize(exporterState);
        Logger.chatMessage(EnumChatFormatting.AQUA + "Active plugins:");
        activePlugins.keySet().forEach(
                plugin -> Logger.chatMessage("  " + EnumChatFormatting.YELLOW + plugin.getName()));
        Logger.chatMessage(EnumChatFormatting.AQUA + "Exporting data...");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(new Metadata(activePlugins.keySet()));

        registry.initializePlugins();
        registry.processPlugins();
        registry.postProcessPlugins();

        Logger.chatMessage(EnumChatFormatting.AQUA + "Data exported!");
        Logger.chatMessage(EnumChatFormatting.AQUA + "Committing database...");
        transaction.commit();

        // SHUTDOWN is an HSQLDB command, so don't run it on PostgreSQL.
        if (!ConfigOptions.USE_POSTGRESQL.get()) {
            Logger.chatMessage(EnumChatFormatting.AQUA + "Compacting database...");
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("SHUTDOWN COMPACT").executeUpdate();
            // No need to commit the transaction; SHUTDOWN closes everything already.
        }

        entityManager.close();
        entityManagerFactory.close();
        Logger.chatMessage(EnumChatFormatting.AQUA + "Commit complete!");

        if (renderingImages) {
            Logger.chatMessage(EnumChatFormatting.AQUA + "Waiting for rendering to finish...");
            Logger.MOD.info("Remaining render jobs: " + RenderDispatcher.INSTANCE.getJobCount());
            try {
                RenderDispatcher.INSTANCE.waitUntilJobsComplete();
            } catch (InterruptedException wakeUp) {}
            RenderDispatcher.INSTANCE.setRendererState(RenderDispatcher.RendererState.DESTROYING);
            Logger.chatMessage(EnumChatFormatting.AQUA + "Rendering complete!");

            try {
                imageZipFileSystem.close();
            } catch (IOException e) {
                Logger.chatMessage(
                        EnumChatFormatting.RED
                                + String.format(
                                        "Caught exception trying to close image zip file:\n%s",
                                        imageZipFile.getPath()));
                e.printStackTrace();
            }
        }
        Logger.chatMessage(EnumChatFormatting.AQUA + "Export complete!");
    }
}
