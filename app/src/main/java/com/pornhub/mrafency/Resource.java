package com.pornhub.mrafency;

import android.graphics.Color;

public enum Resource {
    BRICKS(R.drawable.brick, "Cihly", Color.parseColor("#880000")),         // 0
    BUILDERS(R.drawable.builder, "Stavitelé", Color.parseColor("#880000")), // 1
    WEAPONS(R.drawable.weapon, "Zbraně", Color.parseColor("#008800")),      // 2
    SOLDIERS(R.drawable.soldier, "Vojáci", Color.parseColor("#008800")),    // 3
    CRYSTALS(R.drawable.crystal, "Krystaly", Color.parseColor("#000088")),  // 4
    WIZARDS(R.drawable.wand, "Mágové", Color.parseColor("#000088")),        // 5
    WALL(R.drawable.wall, "Hradba", Color.parseColor("#888888")),           // 6
    CASTLE(R.drawable.castle, "Hrad", Color.parseColor("#888888"));         // 7

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
