package com.pornhub.mrafency.states;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import com.pornhub.mrafency.CardManager;
import com.pornhub.mrafency.GameSide;
import com.pornhub.mrafency.Player;
import com.pornhub.mrafency.R;
import com.pornhub.mrafency.Gui;
import com.pornhub.mrafency.Resource;

public class PlayState implements GameState {

    private View view;
    private Gui gui;
    private Player[] players = new Player[2];

    public PlayState(View view) {
        this.view = view;

        CardManager.getInstance().loadCards(view.getContext());

        players[0] = new Player(view, GameSide.LEFT);
        players[1] = new Player(view, GameSide.RIGHT);

        gui = new Gui(view);
    }

    private void setupPlayer(Player player) {
        player.setResource(Resource.BRICKS, 10);
        player.setResource(Resource.BUILDERS, 2);

        player.setResource(Resource.WEAPONS, 10);
        player.setResource(Resource.SOLDIERS, 2);

        player.setResource(Resource.CRYSTALS, 10);
        player.setResource(Resource.WIZARDS, 2);

        player.setResource(Resource.WALL, 10);
        player.setResource(Resource.CASTLE, 20);
    }

    @Override
    public void init() {
        setupPlayer(getPlayer1());
        setupPlayer(getPlayer2());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.bg), null, new Rect(0, 0, view.getWidth(), view.getHeight()), null);
        gui.draw(canvas);
        getPlayer1().draw(canvas);
        getPlayer2().draw(canvas);
    }

    @Override
    public void update() {

    }

    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2() {
        return players[1];
    }
}
