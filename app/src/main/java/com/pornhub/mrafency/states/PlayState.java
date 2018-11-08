package com.pornhub.mrafency.states;

<<<<<<< HEAD
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;

import com.pornhub.mrafency.GameSurface;
import com.pornhub.mrafency.R;

public class PlayState implements GameState {

    private SurfaceView view;

    public PlayState(SurfaceView view) {
        this.view = view;
    }

    @Override
    public void init() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(BitmapFactory.decodeResource(view.getResources(), R.drawable.bg), null, new Rect(0, 0, view.getWidth(), view.getHeight()), null);
=======
import android.graphics.Canvas;

public class PlayState implements GameState {

    @Override
    public void draw(Canvas canvas) {

>>>>>>> master
    }

    @Override
    public void update() {

    }
}
