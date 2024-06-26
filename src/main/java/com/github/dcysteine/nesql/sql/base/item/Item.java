package com.github.dcysteine.nesql.sql.base.item;

import com.github.dcysteine.nesql.sql.Identifiable;
import com.github.dcysteine.nesql.sql.Metadata;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;
import java.util.Map;

@Entity
@EqualsAndHashCode
@Getter
@ToString
public class Item implements Identifiable<String> {
    /**
     * This is the unique table key, NOT the Minecraft item ID! The latter is not unique (there can
     * be multiple item rows for the same Minecraft item ID).
     */
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String imageFilePath;

    @Column(nullable = false)
    private String modId;

    @Column(nullable = false)
    private String internalName;

    @Column(nullable = false)
    private String unlocalizedName;

    @Column(nullable = false)
    private String localizedName;

    /** The Minecraft item ID. These IDs can vary from world to world, so don't rely on them! */
    private int itemId;

    private int itemDamage;

    @Column(length = Metadata.MAX_STRING_LENGTH, nullable = false)
    private String nbt;

    @Column(length = Metadata.MAX_STRING_LENGTH, nullable = false)
    private String tooltip;

    private int maxStackSize;

    private int maxDamage;

    /** Map of tool class to harvest level. */
    @ElementCollection
    private Map<String, Integer> toolClasses;

    /** Needed by Hibernate. */
    protected Item() {}

    public Item(
            String id,
            String imageFilePath,
            String modId,
            String internalName,
            String unlocalizedName,
            String localizedName,
            int itemId,
            int itemDamage,
            String nbt,
            String tooltip,
            int maxStackSize,
            int maxDamage,
            Map<String, Integer> toolClasses) {
        this.id = id;
        this.imageFilePath = imageFilePath;
        this.modId = modId;
        this.internalName = internalName;
        this.unlocalizedName = unlocalizedName;
        this.localizedName = localizedName;
        this.itemId = itemId;
        this.itemDamage = itemDamage;
        this.nbt = nbt;
        this.tooltip = tooltip;
        this.maxStackSize = maxStackSize;
        this.maxDamage = maxDamage;
        this.toolClasses = toolClasses;
    }

    public boolean hasNbt() {
        return !nbt.isEmpty();
    }

    @Override
    public int compareTo(Identifiable<String> other) {
        if (other instanceof Item) {
            return Comparator.comparing(Item::getModId)
                    .thenComparing(Item::getInternalName)
                    .thenComparing(Item::getItemDamage)
                    .thenComparing(Item::getNbt, Comparator.naturalOrder())
                    .thenComparing(Item::getId)
                    .compare(this, (Item) other);
        } else {
            return Identifiable.super.compareTo(other);
        }
    }
}