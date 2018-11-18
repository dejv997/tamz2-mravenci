package com.pornhub.mrafency.objects;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

import com.pornhub.mrafency.Drawable;

public class Gui implements Drawable {

    private ResourceInfo leftResourceInfo;
    private ResourceInfo rightResourceInfo;

    public Gui(View view) {
        leftResourceInfo = new ResourceInfo(view, true);
        rightResourceInfo = new ResourceInfo(view, false);

    }

    @Override
    public void draw(Canvas canvas) {
        leftResourceInfo.draw(canvas);
        rightResourceInfo.draw(canvas);


    }
}
