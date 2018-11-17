package com.pornhub.mrafency.states;

import android.graphics.Canvas;
import android.view.View;

import com.pornhub.mrafency.Drawable;
import com.pornhub.mrafency.Updatable;

import java.util.HashMap;
import java.util.Map;

public class GameStateManager implements Drawable, Updatable {

    private static GameStateManager self;
    private State currentState;
    private Map<State, GameState> states = new HashMap<>();

    public static synchronized GameStateManager getInstance() {
        if(self == null) {
            self = new GameStateManager();
        }
        return self;
    }

    public void putState(State state, GameState gameState) {
        states.put(state, gameState);
    }

    public void switchState(State state) {
        currentState = state;
    }

    public void draw(Canvas canvas) {
        states.get(currentState).draw(canvas);
    }

    public void update() {
        states.get(currentState).update();
    }

}
