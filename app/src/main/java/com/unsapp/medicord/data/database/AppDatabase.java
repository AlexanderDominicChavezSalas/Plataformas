package com.unsapp.medicord.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.unsapp.medicord.data.dao.UserDao;
import com.unsapp.medicord.data.models.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //private  static final  String NAME_DB = "medicord_db";
    public abstract UserDao userDao();

    /*
    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(final Context context){
        if (instance == null){
            synchronized (AppDatabase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, NAME_DB)
                            .build();
                }
            }
        }
        return instance;
    }
     */

}
