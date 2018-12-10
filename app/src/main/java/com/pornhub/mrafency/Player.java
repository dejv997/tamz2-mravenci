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
    private CardBack[] cardBacks = new CardBack[CARD_COUNT];
    private ResourceInfo resourceInfo;
    private Player opponent = null;
    private boolean showCards;

    public Player(View view, GameSide side, boolean showCards) {
        this.showCards = showCards;
        resourceInfo = new ResourceInfo(view, this, side);
        for(int i = 0; i < cards.length; i++) {
            cards[i] = new GameCard(view, cardManager.getRandomCard(), i);
            cardBacks[i] = new CardBack(view, i);
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
        if(showCards) {
            for(int i = 0; i < cards.length; i++) {
                if(cards[i] != null) {
                    cards[i].draw(canvas);
                } else {
                    cardBacks[i].draw(canvas);
                }
            }
        } else {
            for(int i = 0; i < cards.length; i++) {
                cardBacks[i].draw(canvas);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        resourceInfo.draw(canvas);
    }

    public Card useCard(float x, float y) {
        for(int i = 0; i < cards.length; i++) {
            GameCard card = cards[i];
            if(card != null) {
                if(x >= card.getRect().left && x <= card.getRect().right && y >= card.getRect().top && y <= card.getRect().bottom) {
                    useCard(i);
                    return card.getCard();
                }
            }
        }
        return null;
    }

    private void useCard(int i) {
        cards[i].getCard().use(this, opponent);
        cards[i] = null;
    }

    public Card useRandomCard() {
        return null;
    }
}
