package com.example.ciclobnb.BBDD;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Usser.class}, version = 2, exportSchema = false)

abstract class ConneccioBBDD extends RoomDatabase {
        abstract UsserDAO userDao();

        // marking the instance as volatile to ensure atomic access to the variable
        private static volatile ConneccioBBDD INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        static ConneccioBBDD getDatabase(final Context context) {
                if (INSTANCE == null) {
                        synchronized (ConneccioBBDD.class) {
                                if (INSTANCE == null) {
                                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                                        ConneccioBBDD.class, "CicloBnB")
                                                .fallbackToDestructiveMigration()
                                                .addCallback(sRoomDatabaseCallback)
                                                .build();
                                }
                        }
                }
                return INSTANCE;
        }
        private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        databaseWriteExecutor.execute(() -> {
                                // Populate the database in the background.
                                // If you want to start with more words, just add them.
                                UsserDAO dao = INSTANCE.userDao();
                                dao.deleteAll();

                                Usser user = new Usser("Norbert","Aguilera","Capdevila","norman","1234","20/08/2003","naca605@gmail.com",true,1);
                               // @NonNull String cognom2, @NonNull String login, @NonNull String contrasenya, @NonNull String dataNaixement, @NonNull String correuElectronic, @NonNull Boolean actiu, int idDireccio
                                dao.insert(user);

                        });
                }
        };
}
