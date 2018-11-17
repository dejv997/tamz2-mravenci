package com.pornhub.mrafency.states;

import android.graphics.Canvas;

import com.pornhub.mrafency.Drawable;
import com.pornhub.mrafency.Updatable;

public interface GameState extends Drawable, Updatable {
    void init();
    void draw(Canvas canvas);
    void update();
}
