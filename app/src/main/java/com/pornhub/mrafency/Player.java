package com.pornhub.mrafency;

import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Player implements Drawable {
    public static int CARD_COUNT = 8;
    private Map<Resource, Integer> resources = new HashMap<>();
    private CardManager cardManager = CardManager.getInstance();
    private GameCard[] cards = new GameCard[CARD_COUNT];
    private CardBack[] cardBacks = new CardBack[CARD_COUNT];
    private ResourceInfo resourceInfo;
    private Player opponent = null;
    private boolean showCards;
    private Random rand = new Random();
    private boolean destroyed = false;

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
        if(value < 0) {
            value = 0;
        }
        setResource(resource, value);
    }

    public void incrementResources(int amount) {
        for(Resource resource : resources.keySet()) {
            if(resource != Resource.WALL && resource != Resource.CASTLE) {
                incrementResource(resource, amount);
            }
        }
    }

    public void incrementSupplies(int amount) {
        for(Resource resource : resources.keySet()) {
            if(resource == Resource.BRICKS || resource == Resource.CRYSTALS || resource == Resource.SOLDIERS) {
                incrementResource(resource, amount);
            }
        }
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

    public int getCardIndex(float x, float y) {
        for(int i = 0; i < cards.length; i++) {
            GameCard card = cards[i];
            if(card != null) {
                if(x >= card.getRect().left && x <= card.getRect().right && y >= card.getRect().top && y <= card.getRect().bottom) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Card useCard(int i) {
        Card card = cards[i].getCard();
        if(canUse(i)) {
            incrementResource(card.getPriceResource(), -card.getPriceAmount());
            card.use(this, opponent);
        }
        cards[i].setCard(cardManager.getRandomCard());
        return card;
    }

    public boolean canUse(int i) {
        return resources.get(cards[i].getCard().getPriceResource()) >= cards[i].getCard().getPriceAmount();
    }

    public int getRandomPlayableCad() {
        List<Integer> playableCards = new ArrayList<>();
        for(int i = 0; i < cards.length; i++) {
            if(canUse(i)) {
                playableCards.add(i);
            }
        }
        if(playableCards.size() == 0) {
            return -1;
        }
        return playableCards.get(rand.nextInt(playableCards.size()));
    }

    public Card discardRandomCard() {
        int index = rand.nextInt(cards.length);
        Card card = cards[index].getCard();
        cards[index].setCard(cardManager.getRandomCard());
        return card;
    }

    public void damage(int amount) {
        if(amount > resources.get(Resource.WALL)) {
            incrementResource(Resource.CASTLE, -(amount - resources.get(Resource.WALL)));
            setResource(Resource.WALL, 0);
        } else {
            incrementResource(Resource.WALL, -amount);
        }

        if(resources.get(Resource.CASTLE) <= 0) {
            destroyed = true;
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
