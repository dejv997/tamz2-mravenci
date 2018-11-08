package com.pornhub.mrafency.states;

import android.graphics.Canvas;

<<<<<<< HEAD
import com.pornhub.mrafency.Drawable;
import com.pornhub.mrafency.Updatable;

public interface GameState extends Drawable, Updatable {
    void init();
=======
public interface GameState {
>>>>>>> master
    void draw(Canvas canvas);
    void update();
}
