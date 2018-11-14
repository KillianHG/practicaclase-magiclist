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
    private final int PAGES = 5;


    //or |
    //and ,
    ArrayList<Cartas> getCartasLaReunion() {
        return doCall();
    }

    private String getUrlPage(int pagina) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .appendQueryParameter("page", String.valueOf(pagina))
                .build();
        return builtUri.toString();
    }


    /*ArrayList<Cartas> getCartasLaReunionByRarity(String rarity) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .appendQueryParameter("rarity", rarity)
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }*/

    /*ArrayList<Cartas> getCartasLaReunionByColor(String color) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("cards")
                .appendQueryParameter("colors", color)
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }*/

    private ArrayList<Cartas> doCall() {
        ArrayList<Cartas> cartas = new ArrayList<>();

        for (int i = 0; i < PAGES; i++) {
            try {
                String url = getUrlPage(i);
                String JsonResponse = HttpUtils.get(url);
                ArrayList<Cartas> list = processJson(JsonResponse);
                cartas.addAll(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cartas;
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
                if(!jsonCard.getString("colors").equals(null)){
                    carta.setColors(jsonCard.getString("colors"));
                } else {
                    carta.setColors(null);
                }
                carta.setRarity(jsonCard.getString("rarity"));
                if (!jsonCard.getString("imageUrl").equals(null)) {
                carta.setImageUrl(jsonCard.getString("imageUrl"));
                } else {
                    carta.setImageUrl(null);
                }
                carta.setMultiverseId(jsonCard.getInt("multiverseid"));
                cartas.add(carta);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }
}

