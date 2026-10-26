package com.github.dcysteine.nesql.exporter.plugin.gregtech.util;

import gregtech.api.util.OreDictUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GregTechUtil {
    // Static class.
    private GregTechUtil() {}

    /** Returns interchangeable recipe inputs for the provided item stack. */
    public static List<ItemStack> reverseUnify(ItemStack itemStack) {
        return itemStack == null ? Collections.emptyList() : OreDictUtils.getInterchangeableStacks(itemStack);
    }

    /** Returns the accepted alternatives for a stack, stack array or list of stacks. */
    public static List<ItemStack> reverseUnify(Object object) {
        if (object instanceof ItemStack) {
            return reverseUnify((ItemStack) object);
        }
        List<?> stacks;
        if (object instanceof ItemStack[]) {
            stacks = Arrays.asList((ItemStack[]) object);
        } else if (object instanceof List<?>) {
            stacks = (List<?>) object;
        } else {
            return Collections.emptyList();
        }
        List<ItemStack> alternatives = new ArrayList<>();
        for (Object stack : stacks) {
            alternatives.addAll(reverseUnify((ItemStack) stack));
        }
        return alternatives;
    }
}
