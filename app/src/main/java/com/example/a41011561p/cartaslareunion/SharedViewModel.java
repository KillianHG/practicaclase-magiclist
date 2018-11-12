package com.example.a41011561p.cartaslareunion;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by carlesgm on 29/8/17.
 */

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Cartas> selected = new MutableLiveData<Cartas>();

    public void select(Cartas cartas) {
        selected.setValue(cartas);
    }

    public LiveData<Cartas> getSelected() {
        return selected;
    }
}

