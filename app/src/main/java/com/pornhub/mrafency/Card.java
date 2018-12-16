package com.pornhub.mrafency;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import java.util.List;

public class Card {
    private final int id;
    private String name;
    private Resource priceResource;
    private int priceAmount;
    private List<CardAction> actions;
    private String actionText;
    private Context context;

    public Card(Context context, int id, String name, Resource priceResource, int priceAmount, List<CardAction> actions) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.priceResource = priceResource;
        this.priceAmount = priceAmount;
        this.actions = actions;
        generateActionText();
    }

    private void generateActionText() {
        StringBuilder sb = new StringBuilder();
        for(CardAction action : actions) {
            switch(action.getType()) {
                case ATTACK:
                    sb.append("Útok ").append(action.getAmount());
                    break;
                case STEAL_SUPPLIES:
                    sb.append("Převod\nzásob\nsoupeře ");
                    sb.append(action.getAmount());
                    break;
                case MODIFY_RESOURCE:
                    sb.append(action.getResource().getName())
                            .append(" ");
                    if(action.getTarget() == CardActionTarget.OPPONENT) {
                        sb.append("soupeře ");
                    }
                    sb.append(getNumberWithSymbol(action.getAmount()));
                    break;
                case MODIFY_RESOURCES:
                    sb.append("Vše ");
                    if(action.getTarget() == CardActionTarget.OPPONENT) {
                        sb.append("soupeře ");
                    }
                    sb.append(getNumberWithSymbol(action.getAmount()));
                    break;
                case MODIFY_SUPPLIES:
                    sb.append("Zásoby soupeře ");
                    sb.append(getNumberWithSymbol(action.getAmount()));
                    break;
            }
            sb.append("\n");
        }
        actionText = sb.toString();
    }

    private String getNumberWithSymbol(int n) {
        if(n > 0) {
            return "+" + Integer.toString(n);
        } else {
            return Integer.toString(n);
        }
    }

    public void use(Player owner, Player opponent) {
        SharedPreferences preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean sounds = preferences.getBoolean("sounds", true);

        MediaPlayer mp = MediaPlayer.create(context, R.raw.card);
        mp.setVolume((sounds ? 1 : 0), (sounds ? 1 : 0));

        for(CardAction action : actions) {
            Player target = null;
            if(action.getTarget() == CardActionTarget.SELF) {
                target = owner;
            } else if(action.getTarget() == CardActionTarget.OPPONENT) {
                target = opponent;
            }
            MediaPlayer effect = null;
            switch(action.getType()) {
                case ATTACK:
                    opponent.damage(action.getAmount());
                    effect = MediaPlayer.create(context, R.raw.attack);
                    break;
                case STEAL_SUPPLIES:
                    opponent.incrementSupplies(-action.getAmount());
                    target.incrementSupplies(action.getAmount());
                    effect = MediaPlayer.create(context, R.raw.other);
                    break;
                case MODIFY_RESOURCE:
                    target.incrementResource(action.getResource(), action.getAmount());
                    if(getPriceResource() == Resource.BRICKS) {
                        effect = MediaPlayer.create(context, R.raw.stone);
                    } else {
                        effect = MediaPlayer.create(context, R.raw.other);
                    }
                    break;
                case MODIFY_RESOURCES:
                    target.incrementResources(action.getAmount());
                    effect = MediaPlayer.create(context, R.raw.other);
                    break;
                case MODIFY_SUPPLIES:
                    target.incrementSupplies(action.getAmount());
                    effect = MediaPlayer.create(context, R.raw.other);
                    break;
            }
            effect.setVolume((sounds ? 1 : 0), (sounds ? 1 : 0));
            mp.setNextMediaPlayer(effect);
        }
        mp.start();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Resource getPriceResource() {
        return priceResource;
    }

    public int getPriceAmount() {
        return priceAmount;
    }

    public List<CardAction> getActions() {
        return actions;
    }

    public String getActionText() {
        return actionText;
    }
}
