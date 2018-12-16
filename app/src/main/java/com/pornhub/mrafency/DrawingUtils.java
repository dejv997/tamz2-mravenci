package com.pornhub.mrafency;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DrawingUtils {
    public static void drawMutiLineText(Canvas canvas, String text, Point pos, Paint paint) {
        String[] lines = text.split("\n");
        int x = pos.x;
        int y = pos.y;
        for(String line : lines) {
            canvas.drawText(line, x, y, paint);
            y += paint.descent() - paint.ascent();
        }
    }

    public static void drawMutiLineTextBottom(Canvas canvas, String text, Point pos, Paint paint) {
        String[] lines = text.split("\n");
        List<String> linesList = Arrays.asList(lines);
        Collections.reverse(linesList);
        int x = pos.x;
        int y = pos.y;
        for(String line : linesList) {
            canvas.drawText(line, x, y, paint);
            y -= paint.descent() - paint.ascent();
        }
    }
}
