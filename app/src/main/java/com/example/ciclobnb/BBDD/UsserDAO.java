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

    @Query("SELECT * FROM usuari WHERE IdUser = :identificador")
    LiveData<UsserBBDD> getWord(int identificador);

    @Query("SELECT * FROM usuari WHERE contrasenya= :pass AND login= :login")
    UsserBBDD getLogin(String login,String pass);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UsserBBDD user);

    @Update
    void update(UsserBBDD user);


    @Query("DELETE FROM usuari")
    void deleteAll();

}
