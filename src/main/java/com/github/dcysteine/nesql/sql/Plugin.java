package com.github.dcysteine.nesql.sql;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;

public enum Plugin {
    BASE("base"),
    MINECRAFT("minecraft"),
    NEI("nei"),
    FORGE("forge"),

    MOBS_INFO("mobsinfo"),

    AVARITIA("avaritia"),
    GREGTECH("gregtech"),
    THAUMCRAFT("thaumcraft"),
    QUEST("quest"),
    ;

    public static final ImmutableList<String> NAMES;

    static {
        ImmutableList.Builder<String> builder = ImmutableList.builder();
        Arrays.stream(values()).map(Plugin::getName).forEach(builder::add);
        NAMES = builder.build();
    }

    private final String name;

    Plugin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
