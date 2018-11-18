package com.pornhub.mrafency;

import android.graphics.Color;

public enum Resource {
    BRICKS(R.drawable.brick, "Cihly", Color.RED),           // 0
    BUILDERS(R.drawable.builder, "Stavitelé", Color.RED),   // 1
    WEAPONS(R.drawable.weapon, "Zbraně", Color.GREEN),      // 2
    SOLDIERS(R.drawable.soldier, "Vojáci", Color.GREEN),    // 3
    CRYSTALS(R.drawable.crystal, "Krystaly", Color.BLUE),   // 4
    WIZARDS(R.drawable.wand, "Mágové", Color.BLUE),         // 5
    WALL(R.drawable.wall, "Hradba", Color.DKGRAY),          // 6
    CASTLE(R.drawable.castle, "Hrad", Color.DKGRAY);        // 7

    private final int imageResource;
    private final String name;
    private final int color;

    Resource(int imageResource, String name, int color) {
        this.imageResource = imageResource;
        this.name = name;
        this.color = color;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public static Resource fromValue(int value) throws IllegalArgumentException {
        try {
            return Resource.values()[value];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}
