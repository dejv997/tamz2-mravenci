package com.pornhub.mrafency;

public enum CardActionType {
    STEAL_SUPPLIES,
    MODIFY_SUPPLIES,
    MODIFY_RESOURCES,
    MODIFY_GENERATORS,
    ATTACK;

    public static CardActionType fromValue(int value) throws IllegalArgumentException {
        try {
            return CardActionType.values()[value];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}
