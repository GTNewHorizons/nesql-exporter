package com.github.dcysteine.nesql.exporter.plugin.base.factory;

import com.github.dcysteine.nesql.exporter.main.Logger;
import com.github.dcysteine.nesql.exporter.main.config.ConfigOptions;
import com.github.dcysteine.nesql.exporter.plugin.EntityFactory;
import com.github.dcysteine.nesql.exporter.util.IdUtil;
import com.github.dcysteine.nesql.exporter.util.ItemUtil;
import com.github.dcysteine.nesql.exporter.util.StringUtil;
import com.github.dcysteine.nesql.exporter.util.render.RenderDispatcher;
import com.github.dcysteine.nesql.exporter.util.render.RenderJob;
import com.github.dcysteine.nesql.exporter.util.render.Renderer;
import com.github.dcysteine.nesql.sql.base.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import jakarta.persistence.EntityManager;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ItemFactory extends EntityFactory<Item, String> {
    public ItemFactory(EntityManager entityManager) {
        super(entityManager);
    }

    public Item getItem(ItemStack itemStack) {
        // We're just exporting data, not actually doing recipe matching, so I think we can just
        // ignore wildcard NBT. It probably isn't handled by most recipe types, anyway.
        String nbt = null;
        if (itemStack.hasTagCompound() && !ItemUtil.isWildcardNbt(itemStack.getTagCompound())) {
            nbt = itemStack.getTagCompound().toString();
        }

        Item item;
        try {
            @SuppressWarnings("unchecked")
            String tooltip =
                    ((List<String>) itemStack.getTooltip(Minecraft.getMinecraft().thePlayer, true))
                            .stream()
                            .map(StringUtil::stripFormatting)
                            .collect(Collectors.joining("\n"));

            item = new Item(
                    IdUtil.itemId(itemStack),
                    StringUtil.formatFilePath(IdUtil.imageFilePath(itemStack)),
                    IdUtil.modId(itemStack),
                    GameRegistry.findUniqueIdentifierFor(itemStack.getItem()).name,
                    itemStack.getUnlocalizedName(),
                    StringUtil.stripFormatting(itemStack.getDisplayName()),
                    ItemUtil.getItemId(itemStack),
                    itemStack.getItemDamage(),
                    nbt,
                    tooltip,
                    itemStack.getMaxStackSize(),
                    itemStack.getMaxDamage(),
                    ItemUtil.getToolClasses(itemStack));
        } catch (Exception e) {
            // Sometimes items will throw exceptions when you try to get their name or tooltip.
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            String stackTrace = stringWriter.toString();

            item = new Item(
                    IdUtil.itemId(itemStack),
                    StringUtil.formatFilePath(IdUtil.imageFilePath(itemStack)),
                    IdUtil.modId(itemStack),
                    GameRegistry.findUniqueIdentifierFor(itemStack.getItem()).name,
                    "ERROR",
                    "ERROR",
                    ItemUtil.getItemId(itemStack),
                    itemStack.getItemDamage(),
                    nbt,
                    stackTrace,
                    itemStack.getMaxStackSize(),
                    itemStack.getMaxDamage(),
                    new HashMap<>());
            Logger.BASE.error("Caught exception while trying to persist item: {}", item.getId());
            e.printStackTrace();
        }

        if (Renderer.INSTANCE.isUnrenderedItem(item.getId())
                && ConfigOptions.RENDER_ICONS.get()) {
            Logger.intermittentLog(
                    Logger.BASE,
                    "Enqueueing render of item #{}: " + item.getId(),
                    Renderer.INSTANCE.getRenderedItemCount());
            RenderDispatcher.INSTANCE.addJob(RenderJob.ofItem(itemStack));
        }

        return findOrPersist(Item.class, item);
    }
}
