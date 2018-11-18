package com.pornhub.mrafency.objects;

import android.graphics.Color;

import com.pornhub.mrafency.R;

public enum Resource {
    BRICKS(R.drawable.brick, "Cihly", Color.RED),
    BUILDERS(R.drawable.builder, "Stavitelé", Color.RED),
    WEAPONS(R.drawable.weapon, "Zbraně", Color.GREEN),
    SOLDIERS(R.drawable.soldier, "Vojáci", Color.GREEN),
    CRYSTALS(R.drawable.crystal, "Krystaly", Color.BLUE),
    WIZARDS(R.drawable.wand, "Mágové", Color.BLUE),
    WALL(R.drawable.wall, "Hradba", Color.DKGRAY),
    CASTLE(R.drawable.castle, "Hrad", Color.DKGRAY);

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
