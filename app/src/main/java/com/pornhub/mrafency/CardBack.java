package com.pornhub.mrafency;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class CardBack implements Drawable{
    private RectF cardRect;
    private Paint strokePaint;
    private Paint backgroundPaint;

    public CardBack(View view, int index) {
        this(view, index, (int)(view.getHeight() * 0.7));
    }

    public CardBack(View view, int index, int top) {
        float spaceWidth = (float)(view.getWidth() * 0.1 / Player.CARD_COUNT);
        float width = (float)((view.getWidth() * 0.9 - spaceWidth * (Player.CARD_COUNT - 1)) / Player.CARD_COUNT);
        float height = (width / 3) * 4;
        float left = (float)(view.getWidth() * 0.05 + width * index + spaceWidth * index);

        cardRect = new RectF(left, top, left + width, top + height);

        strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(10);
        strokePaint.setColor(Color.DKGRAY);

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.LTGRAY);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(cardRect, 15, 15, backgroundPaint);
        canvas.drawRoundRect(cardRect, 15, 15, strokePaint);
    }

}
