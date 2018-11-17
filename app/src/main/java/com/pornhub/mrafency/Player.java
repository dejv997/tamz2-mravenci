package com.pornhub.mrafency;

import com.pornhub.mrafency.objects.Resource;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private Map<Resource, Integer> resources = new HashMap<>();

    public Player() {

    }

    public int getResource(Resource resource) {
        return resources.get(resource);
    }

    public void incrementResource(Resource resource, int amount) {
        int value = resources.get(resource);
        value += amount;
        resources.put(resource, value);
    }

    public void setResource(Resource resource, int value) {
        resources.put(resource, value);
    }
}
