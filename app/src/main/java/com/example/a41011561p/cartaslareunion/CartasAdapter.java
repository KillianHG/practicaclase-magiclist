package com.example.a41011561p.cartaslareunion;

import android.content.Context;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class CartasAdapter extends ArrayAdapter<Cartas> {

    public CartasAdapter(Context context, int resource, List<Cartas> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenim l'objecte en la possició corresponent
        Cartas carta = getItem(position);
        Log.w("XXXX", carta.toString());

        // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_cartas_row, parent, false);
        }

        // Unim el codi en les Views del Layout
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvRarity = convertView.findViewById(R.id.tvRarity);
        ImageView ivPosterImage = convertView.findViewById(R.id.ivPosterImage);

        // Fiquem les dades dels objectes (provinents del JSON) en el layout
        tvName.setText(carta.getName());
        tvRarity.setText(carta.getRarity());

        Glide.with(getContext()).load(
                carta.getImageUrl()
        ).into(ivPosterImage);


        // Retornem la View replena per a mostrarla
        return convertView;
    }

}
