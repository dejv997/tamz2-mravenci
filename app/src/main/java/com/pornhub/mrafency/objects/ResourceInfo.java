package com.pornhub.mrafency.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

import com.pornhub.mrafency.Drawable;

public class ResourceInfo implements Drawable {

    private InfoBarBundle[] infoBundles = new InfoBarBundle[4];

    public ResourceInfo(View view, boolean left) {
        infoBundles[0] = new InfoBarBundle(
                view,
                new Point((int)(view.getWidth() * (left ? 0.05 : 0.75)), (int)(view.getHeight() * 0.05)),
                (int)(view.getWidth() * 0.2),
                (int)(view.getHeight() * 0.2),
                Color.RED,
                Resource.BUILDERS,
                Resource.BRICKS
        );

        infoBundles[1] = new InfoBarBundle(
                view,
                new Point((int)(view.getWidth() * (left ? 0.05 : 0.75)), (int)(view.getHeight() * 0.05 + view.getHeight() * 0.2)),
                (int)(view.getWidth() * 0.2),
                (int)(view.getHeight() * 0.2),
                Color.GREEN,
                Resource.SOLDIERS,
                Resource.WEAPONS
        );

        infoBundles[2] = new InfoBarBundle(
                view,
                new Point((int)(view.getWidth() * (left ? 0.05 : 0.75)), (int)(view.getHeight() * 0.05 + view.getHeight() * 0.4)),
                (int)(view.getWidth() * 0.2),
                (int)(view.getHeight() * 0.2),
                Color.BLUE,
                Resource.WIZARDS,
                Resource.CRYSTALS
        );

        infoBundles[3] = new InfoBarBundle(
                view,
                new Point((int)(view.getWidth() * (left ? 0.05 : 0.75)), (int)(view.getHeight() * 0.05 + view.getHeight() * 0.7)),
                (int)(view.getWidth() * 0.2),
                (int)(view.getHeight() * 0.2),
                Color.DKGRAY,
                Resource.CASTLE,
                Resource.WALL
        );
    }

    public void setResourceValue(Resource resource, int value) {
        switch(resource) {
            case BUILDERS:
                infoBundles[0].setTopValue(value);
                break;
            case BRICKS:
                infoBundles[0].setBottomValue(value);
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for(InfoBarBundle bundle : infoBundles) {
            bundle.draw(canvas);
        }
    }
}
