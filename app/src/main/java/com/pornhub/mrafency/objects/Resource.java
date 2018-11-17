package com.pornhub.mrafency.objects;

public enum Resource {
    BRICKS, BUILDERS, WEAPONS, SOLDIERS, CRYSTALS, WIZARDS, WALL, CASTLE;

    public static Resource fromValue(int value) throws IllegalArgumentException {
        try {
            return Resource.values()[value];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}
