package com.example.a41011561p.cartaslareunion;

import android.graphics.Movie;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CartasLaReunionAPI {
    private final String BASE_URL = "https://api.magicthegathering.io/v1";

    //or |
    //and ,

    ArrayList<Cartas> getCartasLaReunion() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    ArrayList<Cartas> getCartasLaReunionByRarity(String rarity) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .appendQueryParameter("rarity", rarity)
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    ArrayList<Cartas> getCartasLaReunionByColor(String color) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .appendQueryParameter("colors", color)
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    private ArrayList<Cartas> doCall(String url){
        try {
            String JsonResponse = HttpUtils.get(url);
            return processJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Cartas> processJson (String jsonResponse){

        ArrayList<Cartas> cartas = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonCards = data.getJSONArray("cards");
            for (int i = 0; i < jsonCards.length(); i++) {
                JSONObject jsonCard = jsonCards.getJSONObject(i);

                Cartas carta = new Cartas();
                carta.setName(jsonCard.getString("name"));
                carta.setColors(jsonCard.getString("colors"));
                carta.setRarity(jsonCard.getString("rarity"));
                carta.setImageUrl(jsonCard.getString("imageUrl"));
                carta.setMultiverseid(jsonCard.getInt("multiverseid"));
                cartas.add(carta);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }
}

