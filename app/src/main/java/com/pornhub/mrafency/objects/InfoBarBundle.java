package com.pornhub.mrafency.objects;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

import com.pornhub.mrafency.Drawable;

public class InfoBarBundle implements Drawable {

    private int width;
    private int height;
    private Paint backgroundPaint;
    private Paint dividerPaint;
    private Point position;
    private InfoBar top;
    private InfoBar bottom;

    public InfoBarBundle(View view, Point position, int width, int height, int backgroundColor, Resource top, Resource bottom) {
        this.position = position;
        this.width = width;
        this.height = height;

        this.top = new InfoBar(
                BitmapFactory.decodeResource(view.getResources(), top.getImageResource()),
                this.position,
                width,
                (int)(height / 2)
        );

        this.bottom = new InfoBar(
                BitmapFactory.decodeResource(view.getResources(), bottom.getImageResource()),
                new Point(position.x, position.y + height / 2),
                width,
                (int)(height / 2)
        );

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(backgroundColor);

        dividerPaint = new Paint();
        dividerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        dividerPaint.setStrokeWidth(5);
        dividerPaint.setPathEffect(new DashPathEffect(new float[] {4, 4}, 50));
        dividerPaint.setColor(Color.BLACK);
    }

    public void setTopValue(int value) {
        top.setValue(value);
    }

    public void setBottomValue(int value) {
        bottom.setValue(value);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(position.x, position.y, position.x + width, position.y + height, backgroundPaint);
        top.draw(canvas);
        bottom.draw(canvas);
        canvas.drawLine(position.x, position.y + (int)(height / 2), position.x + width, position.y + (int)(height / 2), dividerPaint);
    }
}
