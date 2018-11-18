package com.pornhub.mrafency;

import android.graphics.Canvas;
import android.view.View;

import com.pornhub.mrafency.states.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Drawable {
    private Map<Resource, Integer> resources = new HashMap<>();
    private CardManager cardManager = CardManager.getInstance();
    private List<Card> cards = new ArrayList<>();
    private ResourceInfo resourceInfo;

    public Player(View view, GameSide side) {
        resourceInfo = new ResourceInfo(view, this, side);
    }

    public int getResource(Resource resource) {
        return resources.get(resource);
    }

    public void incrementResource(Resource resource, int amount) {
        int value = resources.get(resource);
        value += amount;
        resources.put(resource, value);
    }

    public void setResource(Resource resource, int value) {
        resources.put(resource, value);
    }

    @Override
    public void draw(Canvas canvas) {
        resourceInfo.draw(canvas);
    }
}
