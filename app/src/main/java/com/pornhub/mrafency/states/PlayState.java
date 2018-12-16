package com.pornhub.mrafency.states;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.View;

import com.pornhub.mrafency.BitmapManager;
import com.pornhub.mrafency.Card;
import com.pornhub.mrafency.CardBack;
import com.pornhub.mrafency.CardManager;
import com.pornhub.mrafency.DatabaseManager;
import com.pornhub.mrafency.GameCard;
import com.pornhub.mrafency.GameSide;
import com.pornhub.mrafency.MenuActivity;
import com.pornhub.mrafency.Player;
import com.pornhub.mrafency.PlayerType;
import com.pornhub.mrafency.R;
import com.pornhub.mrafency.Gui;
import com.pornhub.mrafency.Resource;
import com.pornhub.mrafency.Score;
import com.pornhub.mrafency.ScoreActivity;

import java.util.HashMap;
import java.util.Map;

public class PlayState implements GameState {
    private View view;
    private Gui gui;
    private Map<PlayerType, Player> players = new HashMap<>();
    private Bitmap backgroundBitmap;
    private PlayerType onTurn;
    private int waitTicks;
    private CardBack cardPack;
    private GameCard usedCard;
    private RectF leftTeamRect;
    private RectF rightTeamRect;
    private Paint teamRectPaint;
    private Paint leftTeamTextPaint;
    private Point leftTeamTextPos;
    private Paint rightTeamTextPaint;
    private Point rightTeamTextPos;
    private long startTime;

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

        cardPack = new CardBack(view, 3, (int)(view.getHeight() * 0.05));

        leftTeamRect = new RectF(view.getWidth() * 0.22f, view.getHeight() * 0.05f, view.getWidth() * 0.37f, view.getHeight() * 0.12f);
        rightTeamRect = new RectF(view.getWidth() * 0.63f, view.getHeight() * 0.05f, view.getWidth() * 0.78f, view.getHeight() * 0.12f);

        teamRectPaint = new Paint();
        teamRectPaint.setStyle(Paint.Style.FILL);
        teamRectPaint.setColor(Color.WHITE);

        leftTeamTextPaint = new Paint();
        leftTeamTextPaint.setColor(Color.BLACK);
        leftTeamTextPaint.setStyle(Paint.Style.FILL);
        leftTeamTextPaint.setTextSize(view.getHeight() * 0.05f);
        leftTeamTextPaint.setTextAlign(Paint.Align.CENTER);
        leftTeamTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        rightTeamTextPaint = new Paint();
        rightTeamTextPaint.setColor(Color.BLACK);
        rightTeamTextPaint.setStyle(Paint.Style.FILL);
        rightTeamTextPaint.setTextSize(view.getHeight() * 0.05f);
        rightTeamTextPaint.setTextAlign(Paint.Align.CENTER);
        rightTeamTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        leftTeamTextPos = new Point((int)(view.getWidth() * 0.295f), (int)(view.getHeight() * 0.105f));
        rightTeamTextPos = new Point((int)(view.getWidth() * 0.705f), (int)(view.getHeight() * 0.105f));

        startTime = System.currentTimeMillis();
    }

    private void setupPlayer(Player player) {
        player.setResource(Resource.BRICKS, 5);
        player.setResource(Resource.BUILDERS, 2);

        player.setResource(Resource.WEAPONS, 5);
        player.setResource(Resource.SOLDIERS, 2);

        player.setResource(Resource.CRYSTALS, 5);
        player.setResource(Resource.WIZARDS, 2);

        player.setResource(Resource.WALL, 10);
        player.setResource(Resource.CASTLE, 30);
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
        cardPack.draw(canvas);
        if(usedCard != null) {
            usedCard.draw(canvas);
        }

        canvas.drawRect(leftTeamRect, teamRectPaint);
        canvas.drawRect(rightTeamRect, teamRectPaint);
        canvas.drawText("Černí", leftTeamTextPos.x, leftTeamTextPos.y, leftTeamTextPaint);
        canvas.drawText("Červení", rightTeamTextPos.x, rightTeamTextPos.y, rightTeamTextPaint);
    }

    @Override
    public void update() {
        if(waitTicks > 0) {
            waitTicks--;
            if(waitTicks == 0) {
                nextTurn();
            }
        } else {
            if(onTurn == PlayerType.PLAYER2) {
                boolean discarded = false;
                Card card;
                int cardIndex = getPlayer(PlayerType.PLAYER2).getRandomPlayableCad();
                if(cardIndex == -1) {
                    discarded = true;
                    card = getPlayer(PlayerType.PLAYER2).discardRandomCard();
                } else {
                    card = getPlayer(PlayerType.PLAYER2).useCard(cardIndex);
                }
                setUsedCard(card, discarded);
                waitTicks = 180;
            }
        }
    }

    @Override
    public void onTouch(float x, float y) {
        if(waitTicks == 0) {
            if(onTurn == PlayerType.PLAYER1) {
                int index = getPlayer(PlayerType.PLAYER1).getCardIndex(x, y);
                if(index != -1) {
                    boolean discarded = !getPlayer(PlayerType.PLAYER1).canUse(index);
                    Card card = getPlayer(PlayerType.PLAYER1).useCard(index);
                    setUsedCard(card, discarded);
                    waitTicks = 180;
                }
            }
        }
    }

    public Player getPlayer(PlayerType playerType) {
        return players.get(playerType);
    }

    public void nextTurn() {
        if(players.get(PlayerType.PLAYER1).isDestroyed() || players.get(PlayerType.PLAYER2).isDestroyed()) {
            SharedPreferences preferences = view.getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
            boolean sounds = preferences.getBoolean("sounds", true);
            MediaPlayer mp = MediaPlayer.create(view.getContext(), R.raw.win);;
            mp.setVolume((sounds ? 1 : 0), (sounds ? 1 : 0));
            mp.start();

            Intent intent = new Intent(view.getContext(), ScoreActivity.class);
            if(players.get(PlayerType.PLAYER2).isDestroyed()) {
                long time = System.currentTimeMillis() - startTime;
                DatabaseManager.getDatabase(view.getContext()).scoreDao().insert(new Score(time));
                intent.putExtra("time", time);
            }
            view.getContext().startActivity(intent);
        }

        if(onTurn == PlayerType.PLAYER1) {
            onTurn = PlayerType.PLAYER2;
            rightTeamTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            leftTeamTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        } else if(onTurn == PlayerType.PLAYER2) {
            onTurn = PlayerType.PLAYER1;
            leftTeamTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            rightTeamTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        }
        players.get(onTurn).incOnNewTurn();
    }

    private void setUsedCard(Card card, boolean discarded) {
        if(usedCard == null) {
            usedCard = new GameCard(view, card, 4, (int)(view.getHeight() * 0.05), discarded);
        } else {
            usedCard.setCard(card, discarded);
        }
    }
}
