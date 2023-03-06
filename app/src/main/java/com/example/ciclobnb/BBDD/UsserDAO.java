package com.example.ciclobnb.BBDD;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UsserDAO {

    @Query("SELECT * FROM word_table WHERE IdUser = :identificador")
    LiveData<Usser> getWord(int identificador);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Usser user);

    @Update
    void update(Usser user);



    @Query("DELETE FROM word_table")
    void deleteAll();

}
