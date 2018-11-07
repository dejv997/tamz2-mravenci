package com.pornhub.mrafency.states;

import android.graphics.Canvas;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class GameStateManager {
    private State currentState;
    private Map<State, GameState> states = new HashMap<>();

    public GameStateManager() {
        states.put(State.PLAYSTATE, new PlayState());
    }

    public void draw(Canvas canvas) {
        states.get(currentState).draw(canvas);
    }

    public void update() {
        states.get(currentState).update();
    }
}
