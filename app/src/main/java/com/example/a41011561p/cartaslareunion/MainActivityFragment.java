package com.example.a41011561p.cartaslareunion;



import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Cartas> items;
    private CartasAdapter adapter;
    private SharedPreferences preferences;
    private CartasViewModel model;
    private SharedViewModel sharedModel;
    private ProgressDialog dialog;



    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCartas = view.findViewById(R.id.lvCartas);

        items = new ArrayList<>();

        adapter = new CartasAdapter(getContext(), R.layout.lv_cartas_row, items);
        lvCartas.setAdapter(adapter);

        sharedModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        lvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cartas carta = (Cartas) adapterView.getItemAtPosition(i);

                if(!esTablet()){
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("carta", carta);
                startActivity(intent);
                } else {
                    sharedModel.select(carta);
                }
            }
        });

        model = ViewModelProviders.of(this).get(CartasViewModel.class);
        model.getCartas().observe(this, new Observer<List<Cartas>>() {
            @Override
            public void onChanged(@Nullable List<Cartas> cartas) {
                adapter.clear();
                adapter.addAll(cartas);
            }
        });

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");

        model.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean mostrat) {
                if(mostrat)
                    dialog.show();
                else
                    dialog.dismiss();
            }
        });


        return view;
    }

    boolean esTablet() {
        return getResources().getBoolean(R.bool.tablet);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item click here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        model.reload();
    }
}
