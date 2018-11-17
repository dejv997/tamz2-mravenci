package com.pornhub.mrafency.objects;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;

import com.pornhub.mrafency.Drawable;
import com.pornhub.mrafency.R;

public class Gui implements Drawable {

    private InfoBar bricksInfo;
    private InfoBar[] buildersInfo = new InfoBar[Resource.values().length];

    public Gui(View view) {
        bricksInfo = new InfoBar(
                BitmapFactory.decodeResource(view.getResources(), R.drawable.brick),
                Color.RED,
                new Point((int)(view.getWidth() * 0.05), (int)(view.getHeight() * 0.1)),
                (int)(view.getWidth() * 0.2), (int)(view.getWidth() * 0.2 / 3)
        );

    }

    @Override
    public void draw(Canvas canvas) {
        bricksInfo.draw(canvas);
    }
}
