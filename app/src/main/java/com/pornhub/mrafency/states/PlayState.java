package com.pornhub.mrafency.states;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;
import android.view.View;

import com.pornhub.mrafency.GameSurface;
import com.pornhub.mrafency.Player;
import com.pornhub.mrafency.R;
import com.pornhub.mrafency.objects.Gui;

public class PlayState implements GameState {

    private View view;
    private Gui gui;
    private Player[] players = new Player[2];

    public PlayState(View view) {
        this.view = view;

        gui = new Gui(view);

        players[0] = new Player();
        players[1] = new Player();
    }

    @Override
    public void init() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.bg), null, new Rect(0, 0, view.getWidth(), view.getHeight()), null);
        gui.draw(canvas);
    }

    @Override
    public void update() {

    }

    public Player getPlayer() {
        return players[0];
    }

    public Player getOpponent() {
        return players[1];
    }
}
