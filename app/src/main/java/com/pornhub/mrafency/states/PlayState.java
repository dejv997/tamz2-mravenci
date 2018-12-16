package com.pornhub.mrafency.states;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import com.pornhub.mrafency.BitmapManager;
import com.pornhub.mrafency.Card;
import com.pornhub.mrafency.CardManager;
import com.pornhub.mrafency.GameSide;
import com.pornhub.mrafency.Player;
import com.pornhub.mrafency.PlayerType;
import com.pornhub.mrafency.R;
import com.pornhub.mrafency.Gui;
import com.pornhub.mrafency.Resource;

import java.util.HashMap;
import java.util.Map;

public class PlayState implements GameState {
    private View view;
    private Gui gui;
    private Map<PlayerType, Player> players = new HashMap<>();
    private Bitmap backgroundBitmap;
    private PlayerType onTurn;
    private int waitTicks;

    public PlayState(View view) {
        this.view = view;

        CardManager.getInstance().loadCards(view.getContext());

        players.put(PlayerType.PLAYER1, new Player(view, GameSide.LEFT, true));
        players.put(PlayerType.PLAYER2, new Player(view, GameSide.RIGHT, false));

        getPlayer(PlayerType.PLAYER1).setOpponent(getPlayer(PlayerType.PLAYER2));
        getPlayer(PlayerType.PLAYER2).setOpponent(getPlayer(PlayerType.PLAYER1));

        gui = new Gui(view);

        backgroundBitmap = BitmapManager.getInstance().getBitmap(R.drawable.bg);

        onTurn = PlayerType.PLAYER1;
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
        setupPlayer(getPlayer(PlayerType.PLAYER1));
        setupPlayer(getPlayer(PlayerType.PLAYER2));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(backgroundBitmap, null, new Rect(0, 0, view.getWidth(), view.getHeight()), null);
        gui.draw(canvas);
        for(Player player : players.values()) {
            player.draw(canvas);
        }
        getPlayer(PlayerType.PLAYER1).drawCards(canvas);
    }

    @Override
    public void update() {
        if(waitTicks > 0) {
            waitTicks--;
        }
    }

    @Override
    public void onTouch(float x, float y) {
        if(waitTicks == 0) {
            if(onTurn == PlayerType.PLAYER1) {
                int index = getPlayer(PlayerType.PLAYER1).getCardIndex(x, y);
                if(index != -1) {
                    boolean used = getPlayer(PlayerType.PLAYER1).canUse(index);
                    Card card = getPlayer(PlayerType.PLAYER1).useCard(index);
                    waitTicks += 120;
                }
            }
        }
    }

    public Player getPlayer(PlayerType playerType) {
        return players.get(playerType);
    }
}
