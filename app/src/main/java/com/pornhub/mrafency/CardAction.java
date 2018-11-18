package com.pornhub.mrafency;

public class CardAction {
    private CardActionType type;
    private CardActionTarget target;
    private Resource resource;
    private int amount;

    public CardAction(CardActionType type, CardActionTarget target, int amount, Resource resource) {
        this.type = type;
        this.target = target;
        this.resource = resource;
        this.amount = amount;
    }

    public CardActionType getType() {
        return type;
    }

    public CardActionTarget getTarget() {
        return target;
    }

    public Resource getResource() {
        return resource;
    }

    public int getAmount() {
        return amount;
    }
}
