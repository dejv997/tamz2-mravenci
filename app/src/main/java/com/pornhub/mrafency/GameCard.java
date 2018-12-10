package com.pornhub.mrafency;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class GameCard implements Drawable {
    private RectF cardRect;
    private Paint strokePaint;
    private Paint backgroundPaint;
    private Paint pricePaint;
    private Paint namePaint;
    private Paint actionTextPaint;
    private Point priceTextPos;
    private Point nameTextPos;
    private Point actionTextPos;
    private Card card;
    private Bitmap resourceImage;
    private Rect resourceRect;
    private View view;

    public GameCard(View view, Card card, int index) {
        this.view = view;
        float spaceWidth = (float)(view.getWidth() * 0.1 / Player.CARD_COUNT);
        float width = (float)((view.getWidth() * 0.9 - spaceWidth * (Player.CARD_COUNT - 1)) / Player.CARD_COUNT);
        float height = (width / 3) * 4;
        float left = (float)(view.getWidth() * 0.05 + width * index + spaceWidth * index);
        float top = (float)(view.getHeight() * 0.7);
        cardRect = new RectF(left, top, left + width, top + height);

        strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(10);

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);

        pricePaint = new Paint();
        pricePaint.setTextAlign(Paint.Align.RIGHT);
        pricePaint.setColor(Color.WHITE);
        pricePaint.setStyle(Paint.Style.FILL);
        pricePaint.setTextSize(height * 0.2f);

        namePaint = new Paint();
        namePaint.setColor(Color.RED);
        namePaint.setTextAlign(Paint.Align.CENTER);
        namePaint.setStyle(Paint.Style.FILL);
        namePaint.setTextSize(height * 0.1f);

        actionTextPaint = new Paint();
        actionTextPaint.setColor(Color.BLACK);
        actionTextPaint.setTextAlign(Paint.Align.LEFT);
        actionTextPaint.setStyle(Paint.Style.FILL);
        actionTextPaint.setTextSize(height * 0.08f);

        priceTextPos = new Point((int)(left + width * 0.9), (int)(top + height * 0.22));
        nameTextPos = new Point((int)(left + width * 0.5), (int)(top + height * 0.4));
        actionTextPos = new Point((int)(left + width * 0.05), (int)(top + height * 0.95));

        setCard(card);
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
        strokePaint.setColor(card.getPriceResource().getColor());
        backgroundPaint.setColor(ColorUtil.lighten(card.getPriceResource().getColor(), 160));
        resourceImage = BitmapManager.getInstance().getBitmap(card.getPriceResource().getImageResource());
        resourceRect = new Rect(
                (int)(cardRect.left + (cardRect.right - cardRect.left) * 0.07),
                (int)(cardRect.top + (cardRect.right - cardRect.left) * 0.07),
                (int)(cardRect.left + (cardRect.right - cardRect.left) * 0.07 + (cardRect.right - cardRect.left) * 0.25),
                (int)(cardRect.top + (cardRect.right - cardRect.left) * 0.07 + (cardRect.right - cardRect.left) * 0.25)
        );
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRoundRect(cardRect, 15, 15, backgroundPaint);
        canvas.drawRoundRect(cardRect, 15, 15, strokePaint);

        canvas.drawText(Integer.toString(card.getPriceAmount()), priceTextPos.x, priceTextPos.y, pricePaint);
        DrawingUtils.drawMutiLineText(canvas, card.getName().replace(' ', '\n'), nameTextPos, namePaint);
        canvas.drawBitmap(resourceImage, null, resourceRect, null);

        DrawingUtils.drawMutiLineTextBottom(canvas, card.getActionText(), actionTextPos, actionTextPaint);
    }

    public RectF getRect() {
        return cardRect;
    }
}
