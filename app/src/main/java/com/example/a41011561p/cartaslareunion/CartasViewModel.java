package com.example.a41011561p.cartaslareunion;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CartasViewModel extends AndroidViewModel {
    private final Application app;
    private MutableLiveData<List<Cartas>> cartas;

    public CartasViewModel(Application application) {
        super(application);

        this.app = application;
    }

    public LiveData<List<Cartas>> getCartas() {
        Log.d("DEBUG", "ENTRA");

        if (cartas == null) {
            cartas = new MutableLiveData<>();
            reload();
        }
        return cartas;
    }

    public void reload() {
        // do async operation to fetch users
        RefreshDataTask task = new RefreshDataTask();
        task.execute();
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Cartas>> {
        @Override
        protected ArrayList<Cartas> doInBackground(Void... voids) {
            CartasLaReunionAPI api = new CartasLaReunionAPI();

            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(app.getApplicationContext());
            String color = preferences.getString("color", "");
            String rarity = preferences.getString("rarity", "");

            ArrayList<Cartas> result = null;

            if(!rarity.equals("")){
                result = api.getCartasLaReunionByRarity(rarity);
            } else if (!color.equals("")){
                result = api.getCartasLaReunionByColor(color);
            } else {
                result = api.getCartasLaReunion();
            }



            Log.d("DEBUG", result != null ? result.toString() : null);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Cartas> result) {
                cartas.postValue(result);
            }

        }
    }
