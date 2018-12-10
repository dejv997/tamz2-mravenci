package com.pornhub.mrafency;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

public class BitmapManager {
    private static final BitmapManager SELF = new BitmapManager();

    private SparseArray<Bitmap> bitmaps;

    public static BitmapManager getInstance() {
        return SELF;
    }

    private BitmapManager() {
        bitmaps = new SparseArray<>();
    }

    public void loadBitmaps(Resources resources) {

    }

    private void loadBitmap(Resources resources, int resId) {
        bitmaps.put(resId, BitmapFactory.decodeResource(resources, resId));
    }

    public Bitmap getBitmap(int resId) {
        return bitmaps.get(resId);
    }
}
