package com.ujjwalsingh.mvvm.Data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.ujjwalsingh.mvvm.Note.NotesEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;
    public LiveData<List<NotesEntity>> mDataList;

    private Executor executor = Executors.newSingleThreadExecutor();
    private AppDatabase appDatabase;

    public static AppRepository getInstance(Context context) {
        return ourInstance = new AppRepository(context);
    }

    private AppRepository(Context context) {
    appDatabase = AppDatabase.getInstance(context);
    mDataList= getAllNotes();
    }

    public void appData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
            appDatabase.notesDao().insertAll(SampleDataProvidor.addListSample());
            }
        });
    }

    private LiveData<List<NotesEntity>> getAllNotes(){
       return appDatabase.notesDao().getAllNote();
    }
    private LiveData<List<NotesEntity>> getCount(){
       return appDatabase.notesDao().selectAllUsers();
    }

    public void deleteAll() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.notesDao().deleteAllNotes();
            }
        });
    }

    public NotesEntity loadNote(int noteId) {
        return appDatabase.notesDao().getNoteById(noteId);
    }

    public void updateData(final NotesEntity notesEntity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.notesDao().insertNote(notesEntity);
            }
        });
    }
}
