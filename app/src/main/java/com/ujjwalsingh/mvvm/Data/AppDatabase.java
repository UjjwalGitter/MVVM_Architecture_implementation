package com.ujjwalsingh.mvvm.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.ujjwalsingh.mvvm.Note.NotesDao;
import com.ujjwalsingh.mvvm.Note.NotesEntity;

@Database(entities = {NotesEntity.class}, version = 1)
@TypeConverters({DateConvertor.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "notesdatabase.db";
    public static final Object LOCK = new Object();
    public static volatile AppDatabase instance;

    public abstract NotesDao notesDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
