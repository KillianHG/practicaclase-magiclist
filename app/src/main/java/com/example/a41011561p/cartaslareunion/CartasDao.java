package com.example.a41011561p.cartaslareunion;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CartasDao {
    @Query("select * from Cartas")
    LiveData<List<Cartas>> getCartas();

    @Insert
    void addCarta(Cartas carta);

    @Delete
    void deleteCarta(Cartas carta);

    @Query("DELETE FROM cartas")
    void deleteCarta();
}
