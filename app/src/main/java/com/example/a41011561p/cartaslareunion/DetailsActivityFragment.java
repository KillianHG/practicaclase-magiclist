package com.example.a41011561p.cartaslareunion;

import android.content.Intent;
import android.graphics.Movie;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        Intent i = getActivity().getIntent();

        if (i != null) {
            Cartas carta = (Cartas) i.getSerializableExtra("carta");

            if (carta != null) {
                updateUi(carta);
            }
        }

        return view;
    }

    private void updateUi(Cartas carta) {
        Log.d("CARTA", carta.toString());

    }
}
