package com.pornhub.mrafency.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.pornhub.mrafency.Drawable;

public class InfoBar implements Drawable {

    private Bitmap symbol;
    private Point position;
    private int value = 0;
    private int width;
    private int height;
    private Paint valuePaint;
    Rect textBounds = new Rect();

    public InfoBar(Bitmap symbol, Point position, int width, int height) {
        this.symbol = symbol;
        this.position = position;
        this.width = width;
        this.height = height;

        valuePaint = new Paint();
        valuePaint.setColor(Color.WHITE);
        valuePaint.setTextSize((int)(height * 0.8));
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                symbol,
                null,
                new Rect(
                        position.x + (int)(width * 0.15),
                        position.y + (int)(height * 0.05),
                        position.x + (int)(width * 0.15) + (int)(height * 0.95),
                        position.y + (int)(height * 0.95)),
                null
        );

        String valueText = Integer.toString(value);
        valuePaint.getTextBounds(valueText, 0, valueText.length(), textBounds);
        canvas.drawText(valueText, position.x + (int)(width * 0.65), position.y + height / 2 - textBounds.exactCenterY(), valuePaint);

    }
}
