package com.github.dcysteine.nesql.exporter.util;

import com.github.dcysteine.nesql.exporter.proto.FluidGroupPb;
import com.github.dcysteine.nesql.exporter.proto.FluidStackPb;
import com.github.dcysteine.nesql.exporter.proto.ItemGroupPb;
import com.github.dcysteine.nesql.exporter.proto.ItemStackPb;
import com.github.dcysteine.nesql.exporter.proto.RecipePb;
import com.github.dcysteine.nesql.exporter.proto.WildcardItemStackPb;
import com.github.dcysteine.nesql.sql.base.fluid.Fluid;
import com.github.dcysteine.nesql.sql.base.fluid.FluidGroup;
import com.github.dcysteine.nesql.sql.base.fluid.FluidStack;
import com.github.dcysteine.nesql.sql.base.item.Item;
import com.github.dcysteine.nesql.sql.base.item.ItemGroup;
import com.github.dcysteine.nesql.sql.base.item.ItemStack;
import com.github.dcysteine.nesql.sql.base.item.WildcardItemStack;
import com.github.dcysteine.nesql.sql.base.recipe.RecipeType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.SortedSet;

/** Contains shared methods for building protos. */
public final class ProtoBuilder {
    // Static class.
    private ProtoBuilder() {}

    public static RecipePb buildRecipePb(
            RecipeType recipeType,
            List<ItemGroup> itemInputs,
            List<FluidGroup> fluidInputs,
            List<ItemStack> itemOutputs,
            List<FluidStack> fluidOutputs) {
        RecipePb.Builder builder = RecipePb.newBuilder().setRecipeType(recipeType.ordinal());
        itemInputs.forEach(
                itemGroup -> builder.addItemInput(buildItemGroupPb(itemGroup)));
        fluidInputs.forEach(
                fluidGroup -> builder.addFluidInput(buildFluidGroupPb(fluidGroup)));
        itemOutputs.forEach(
                itemStack -> builder.addItemOutput(buildItemStackPb(itemStack)));
        fluidOutputs.forEach(
                fluidStack -> builder.addFluidOutput(buildFluidStackPb(fluidStack)));
        return builder.build();
    }

    public static ItemGroupPb buildItemGroupPb(
            SortedSet<ItemStack> itemStacks, SortedSet<WildcardItemStack> wildcardItemStacks) {
        ItemGroupPb.Builder builder = ItemGroupPb.newBuilder();
        itemStacks.forEach(itemStack -> builder.addItemStack(buildItemStackPb(itemStack)));
        wildcardItemStacks.forEach(
                wildcardItemStack ->
                        builder.addWildcardItemStack(
                                buildWildcardItemStackPb(wildcardItemStack)));
        return builder.build();
    }

    public static ItemGroupPb buildItemGroupPb(ItemGroup itemGroup) {
        ItemGroupPb.Builder builder = ItemGroupPb.newBuilder();
        itemGroup.getItemStacks().stream()
                .map(ProtoBuilder::buildItemStackPb)
                .forEach(builder::addItemStack);
        itemGroup.getWildcardItemStacks().stream()
                .map(ProtoBuilder::buildWildcardItemStackPb)
                .forEach(builder::addWildcardItemStack);
        return builder.build();
    }

    public static FluidGroupPb buildFluidGroupPb(SortedSet<FluidStack> fluidStacks) {
        FluidGroupPb.Builder builder = FluidGroupPb.newBuilder();
        fluidStacks.forEach(fluidStack -> builder.addFluidStack(buildFluidStackPb(fluidStack)));
        return builder.build();
    }

    public static FluidGroupPb buildFluidGroupPb(FluidGroup fluidGroup) {
        FluidGroupPb.Builder builder = FluidGroupPb.newBuilder();
        fluidGroup.getFluidStacks().stream()
                .map(ProtoBuilder::buildFluidStackPb)
                .forEach(builder::addFluidStack);
        return builder.build();
    }

    public static ItemStackPb buildItemStackPb(@Nullable ItemStack itemStack) {
        if (itemStack == null) {
            return ItemStackPb.getDefaultInstance();
        }

        Item item = itemStack.getItem();
        ItemStackPb.Builder builder = ItemStackPb.newBuilder()
                .setItemId(item.getItemId())
                .setDamage(item.getItemDamage())
                .setStackSize(itemStack.getStackSize());
        if (item.hasNbt()) {
            builder.setNbt(item.getNbt());
        }
        return builder.build();
    }

    public static WildcardItemStackPb buildWildcardItemStackPb(
            @Nullable WildcardItemStack wildcardItemStack) {
        if (wildcardItemStack == null) {
            return WildcardItemStackPb.getDefaultInstance();
        }

        return WildcardItemStackPb.newBuilder()
                .setItemId(wildcardItemStack.getItemId())
                .setStackSize(wildcardItemStack.getStackSize())
                .build();
    }

    public static FluidStackPb buildFluidStackPb(@Nullable FluidStack fluidStack) {
        if (fluidStack == null) {
            return FluidStackPb.getDefaultInstance();
        }

        Fluid fluid = fluidStack.getFluid();
        FluidStackPb.Builder builder = FluidStackPb.newBuilder()
                .setFluidId(fluid.getFluidId())
                .setAmount(fluidStack.getAmount());
        if (fluid.hasNbt()) {
            builder.setNbt(fluid.getNbt());
        }
        return builder.build();
    }
}