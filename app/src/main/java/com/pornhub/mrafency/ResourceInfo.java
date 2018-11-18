package com.pornhub.mrafency;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

public class ResourceInfo implements Drawable {

    private InfoBarBundle[] infoBundles = new InfoBarBundle[4];

    public ResourceInfo(View view, Player player, GameSide side) {
        int width = (int)(view.getWidth() * 0.15);
        int height = (int)(view.getHeight() * 0.15);

        infoBundles[0] = new InfoBarBundle(
                view,
                player,
                new Point((int)(view.getWidth() * (side == GameSide.LEFT ? 0.05 : 0.8)), (int)(view.getHeight() * 0.05)),
                width,
                height,
                Resource.BUILDERS,
                Resource.BRICKS
        );

        infoBundles[1] = new InfoBarBundle(
                view,
                player,
                new Point((int)(view.getWidth() * (side == GameSide.LEFT ? 0.05 : 0.8)), (int)(view.getHeight() * 0.05 + height)),
                width,
                height,
                Resource.SOLDIERS,
                Resource.WEAPONS
        );

        infoBundles[2] = new InfoBarBundle(
                view,
                player,
                new Point((int)(view.getWidth() * (side == GameSide.LEFT ? 0.05 : 0.8)), (int)(view.getHeight() * 0.05 + height * 2)),
                width,
                height,
                Resource.WIZARDS,
                Resource.CRYSTALS
        );

        infoBundles[3] = new InfoBarBundle(
                view,
                player,
                new Point((int)(view.getWidth() * (side == GameSide.LEFT ? 0.05 : 0.8)), (int)(view.getHeight() * 0.05 + height * 3)),
                width,
                height,
                Resource.CASTLE,
                Resource.WALL
        );
    }

    @Override
    public void draw(Canvas canvas) {
        for(InfoBarBundle bundle : infoBundles) {
            bundle.draw(canvas);
        }
    }
}
