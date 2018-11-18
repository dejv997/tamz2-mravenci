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
import com.pornhub.mrafency.objects.Resource;

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

    private void setupPlayer(Player player) {
        setPlayerResource(Resource.BRICKS, 10);
        setPlayerResource(Resource.BUILDERS, 2);

        setPlayerResource(Resource.WEAPONS, 10);
        setPlayerResource(Resource.SOLDIERS, 2);

        setPlayerResource(Resource.CRYSTALS, 10);
        setPlayerResource(Resource.WIZARDS, 2);

        setPlayerResource(Resource.WALL, 10);
        setPlayerResource(Resource.CASTLE, 20);


        setOpponentResource(Resource.BRICKS, 10);
        setOpponentResource(Resource.BUILDERS, 2);

        setOpponentResource(Resource.WEAPONS, 10);
        setOpponentResource(Resource.SOLDIERS, 2);

        setOpponentResource(Resource.CRYSTALS, 10);
        setOpponentResource(Resource.WIZARDS, 2);

        setOpponentResource(Resource.WALL, 10);
        setOpponentResource(Resource.CASTLE, 20);
    }

    private void setPlayerResource(Resource resource, int value) {
        getPlayer().setResource(resource, value);
        gui.setPlayerResource(resource, value);
    }

    private void setOpponentResource(Resource resource, int value) {
        getOpponent().setResource(resource, value);
        gui.setOpponentResource(resource, value);
    }

    @Override
    public void init() {
        setupPlayer(getPlayer());
        setupPlayer(getOpponent());
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
