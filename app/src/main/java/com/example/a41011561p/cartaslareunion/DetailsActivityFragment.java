package com.example.a41011561p.cartaslareunion;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsActivityFragment extends Fragment {

    private View view;
    private ImageView ivPosterImage;
    private TextView tvName;
    private TextView tvColor;
    private TextView tvRarity;
    private TextView tvMultiverseid;



    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details, container, false);

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

        ivPosterImage = view.findViewById(R.id.ivPosterImage);
        tvName = view.findViewById(R.id.tvName);
        tvRarity = view.findViewById(R.id.tvRarity);
        tvColor = view.findViewById(R.id.tvColor);
        tvMultiverseid = view.findViewById(R.id.tvMultiverseid);

        tvName.setText(carta.getName());
        tvRarity.setText(carta.getRarity());
        tvColor.setText(carta.getColors().replace("[", "").replace("]","").replace("\"",""));
        tvMultiverseid.setText(Integer.toString(carta.getId()));
        Glide.with(getContext())
                .load(carta.getImageUrl()
        ).into(ivPosterImage);

    }
}
