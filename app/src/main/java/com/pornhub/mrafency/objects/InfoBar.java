package com.pornhub.mrafency.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.pornhub.mrafency.Drawable;

public class InfoBar implements Drawable {

    private Bitmap symbol;
    private int backgroundColor;
    private Point position;
    private int value = 0;
    private int width;
    private int height;
    private Paint backgroundPaint;

    public InfoBar(Bitmap symbol, int backgroundColor, Point position, int width, int height) {
        this.symbol = symbol;
        this.backgroundColor = backgroundColor;
        this.position = position;
        this.width = width;
        this.height = height;

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(backgroundColor);
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(position.x, position.y, position.x + width, position.y + height, backgroundPaint);
    }
}
