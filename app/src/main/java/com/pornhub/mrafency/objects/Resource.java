package com.pornhub.mrafency.objects;

import com.pornhub.mrafency.R;

public enum Resource {
    BRICKS(R.drawable.brick, "Cihly"),
    BUILDERS(R.drawable.builder, "Stavitelé"),
    WEAPONS(R.drawable.weapon, "Zbraně"),
    SOLDIERS(R.drawable.soldier, "Vojáci"),
    CRYSTALS(R.drawable.crystal, "Krystaly"),
    WIZARDS(R.drawable.wand, "Mágové"),
    WALL(R.drawable.brick, "Hradba"),
    CASTLE(R.drawable.brick, "Hrad");

    final int imageResource;
    final String name;

    Resource(int imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public static Resource fromValue(int value) throws IllegalArgumentException {
        try {
            return Resource.values()[value];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}
