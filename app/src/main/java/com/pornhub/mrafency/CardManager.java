package com.pornhub.mrafency;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class CardManager {
    private static final CardManager SELF = new CardManager();
    private final String CARDS_FILE = "cards.json";
    private final Map<Integer, Card> cards = new HashMap<>();
    private Random r = new Random();

    public static CardManager getInstance() {
        return SELF;
    }

    private CardManager() {

    }

    public void loadCards(final Context context) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(CARDS_FILE), "UTF-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        try {
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray cards = jsonObject.getJSONArray("cards");
            // parse that shit

            for (int i = 0; i < cards.length(); i++) {
                JSONObject c = cards.getJSONObject(i);
                int id = c.getInt("id");
                String name = c.getString("name");
                JSONObject price = c.getJSONObject("price");
                Resource priceResource = Resource.fromValue(price.getInt("resource"));
                int priceAmount = price.getInt("amount");
                JSONArray actions = c.getJSONArray("actions");
                List<CardAction> actionList = new ArrayList<>();
                for (int j = 0; j < actions.length(); j++) {
                    JSONObject a = actions.getJSONObject(j);
                    CardActionType type = CardActionType.fromValue(a.getInt("type"));
                    CardActionTarget target = CardActionTarget.fromValue(a.getInt("target"));
                    int amount = a.getInt("amount");
                    Resource resource = null;
                    if(type == CardActionType.MODIFY_RESOURCE) {
                        resource = Resource.fromValue(a.getInt("resource"));
                    }

                    actionList.add(new CardAction(type, target, amount, resource));
                }
                this.cards.put(id, new Card(id, name, priceResource, priceAmount, actionList));

            }

        } catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }
    }

    public Card getRandomCard() {
        int key = (int)cards.keySet().toArray()[r.nextInt(cards.size())];
        return cards.get(key);
    }

    public Card getCard(int id) {
        return cards.get(id);
    }
}
