package com.pornhub.mrafency;

import android.graphics.Canvas;

import com.pornhub.mrafency.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Card implements Drawable {

    private final int id;
    private String name;
    private Resource priceResource;
    private int priceAmount;
    private List<CardAction> actions = new ArrayList<>();

    public Card(int id, String name, Resource priceResource, int priceAmount, List<CardAction> actions) {
        this.id = id;
        this.name = name;
        this.priceResource = priceResource;
        this.priceAmount = priceAmount;
        this.actions = actions;
    }

    public void use(Player owner, Player opponent) {

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
