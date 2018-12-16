package com.pornhub.mrafency;

import java.util.List;

public class Card {
    private final int id;
    private String name;
    private Resource priceResource;
    private int priceAmount;
    private List<CardAction> actions;
    private String actionText;

    public Card(int id, String name, Resource priceResource, int priceAmount, List<CardAction> actions) {
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
        for(CardAction action : actions) {
            Player target = null;
            if(action.getTarget() == CardActionTarget.SELF) {
                target = owner;
            } else if(action.getTarget() == CardActionTarget.OPPONENT) {
                target = opponent;
            }

            switch(action.getType()) {
                case ATTACK:
                    opponent.damage(action.getAmount());
                    break;
                case STEAL_SUPPLIES:
                    opponent.incrementSupplies(-action.getAmount());
                    target.incrementSupplies(action.getAmount());
                    break;
                case MODIFY_RESOURCE:
                    target.incrementResource(action.getResource(), action.getAmount());
                    break;
                case MODIFY_RESOURCES:
                    target.incrementResources(action.getAmount());
                    break;
                case MODIFY_SUPPLIES:
                    target.incrementSupplies(action.getAmount());
                    break;
            }
        }
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
