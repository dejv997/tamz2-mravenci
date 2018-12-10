package com.pornhub.mrafency;

import android.graphics.Canvas;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class Player implements Drawable {
    public static int CARD_COUNT = 8;
    private Map<Resource, Integer> resources = new HashMap<>();
    private CardManager cardManager = CardManager.getInstance();
    private GameCard[] cards = new GameCard[CARD_COUNT];
    private ResourceInfo resourceInfo;
    private Player opponent = null;

    public Player(View view, GameSide side) {
        resourceInfo = new ResourceInfo(view, this, side);
        for(int i = 0; i < cards.length; i++) {
            cards[i] = new GameCard(view, cardManager.getRandomCard(), i);
        }
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return opponent;
    }

    public int getResource(Resource resource) {
        return resources.get(resource);
    }

    public void incrementResource(Resource resource, int amount) {
        int value = resources.get(resource);
        value += amount;
        setResource(resource, value);
    }

    public void setResource(Resource resource, int value) {
        resources.put(resource, value);
        resourceInfo.setResourceValue(resource, value);
    }

    public void drawCards(Canvas canvas) {
        for(GameCard card : cards) {
            card.draw(canvas);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        resourceInfo.draw(canvas);
    }
}
