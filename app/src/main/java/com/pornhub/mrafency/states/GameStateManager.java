package com.pornhub.mrafency.states;

import android.graphics.Canvas;
<<<<<<< HEAD
import android.view.SurfaceView;

import com.pornhub.mrafency.Drawable;
import com.pornhub.mrafency.GameSurface;
import com.pornhub.mrafency.Updatable;

import java.util.HashMap;
import java.util.Map;

public class GameStateManager implements Drawable, Updatable {
    private State currentState;
    private Map<State, GameState> states = new HashMap<>();
    private SurfaceView view;

    public GameStateManager(SurfaceView view) {
        this.view = view;

        states.put(State.PLAYSTATE, new PlayState(view));

        currentState = State.PLAYSTATE;
=======

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class GameStateManager {
    private State currentState;
    private Map<State, GameState> states = new HashMap<>();

    public GameStateManager() {
        states.put(State.PLAYSTATE, new PlayState());
>>>>>>> master
    }

    public void draw(Canvas canvas) {
        states.get(currentState).draw(canvas);
    }

    public void update() {
        states.get(currentState).update();
    }
}
