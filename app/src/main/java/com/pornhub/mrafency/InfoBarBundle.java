package com.pornhub.mrafency;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

import java.util.concurrent.Callable;

public class InfoBarBundle implements Drawable {

    private int width;
    private int height;
    private Paint backgroundPaint;
    private Paint backgroundStrokePaint;
    private Paint dividerPaint;
    private Point position;
    private InfoBar top;
    private InfoBar bottom;
    private Rect backgroundRect;

    public InfoBarBundle(View view, final Player player, Point position, int width, int height, final Resource top, final Resource bottom) {
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
        backgroundPaint.setStrokeWidth(3);
        backgroundPaint.setColor(top.getColor());

        backgroundStrokePaint = new Paint();
        backgroundStrokePaint.setStyle(Paint.Style.STROKE);
        backgroundStrokePaint.setStrokeWidth(3);
        backgroundStrokePaint.setColor(Color.BLACK);

        backgroundRect = new Rect(position.x, position.y, position.x + width, position.y + height);

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
        canvas.drawRect(backgroundRect, backgroundPaint);
        canvas.drawRect(backgroundRect, backgroundStrokePaint);
        top.draw(canvas);
        bottom.draw(canvas);
        canvas.drawLine(position.x, position.y + (int)(height / 2), position.x + width, position.y + (int)(height / 2), dividerPaint);
    }
}
