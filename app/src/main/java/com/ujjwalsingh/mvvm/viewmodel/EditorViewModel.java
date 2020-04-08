package com.ujjwalsingh.mvvm.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.ujjwalsingh.mvvm.Data.AppRepository;
import com.ujjwalsingh.mvvm.Note.NotesEntity;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NotesEntity> liveNote= new MutableLiveData<NotesEntity>();
    private Executor executor = Executors.newSingleThreadExecutor();
    private AppRepository appRepository;

    public EditorViewModel(@NonNull Application application) {
        super(application);
        appRepository=AppRepository.getInstance(application.getApplicationContext());
        }

    public void loadNote(final int noteId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                NotesEntity notesEntity = appRepository.loadNote(noteId);
                liveNote.postValue(notesEntity);
                Log.i("deeee","Sdsd");
            }
        });
    }
    public void updateData(String s) {
        NotesEntity notesEntity = liveNote.getValue();

        if (notesEntity==null){

        }else{
            notesEntity.setText(s.trim());
            notesEntity.setDate(new Date());
            appRepository.updateData(notesEntity);
        }
    }
}
