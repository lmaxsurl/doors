package logunov.maxim.data.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import javax.inject.Singleton;

import logunov.maxim.data.entity.DescriptionResponse;

@Singleton
@Database(entities = {DescriptionResponse.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "description_db";

    public static  AppDatabase getInstance(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract UserDAO getUserDao();

}
