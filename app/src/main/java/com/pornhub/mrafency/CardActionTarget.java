package com.pornhub.mrafency;

public enum CardActionTarget {
    PLAYER1, PLAYER2;

    public static CardActionTarget fromValue(int value) throws IllegalArgumentException {
        try {
            return CardActionTarget.values()[value];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}
