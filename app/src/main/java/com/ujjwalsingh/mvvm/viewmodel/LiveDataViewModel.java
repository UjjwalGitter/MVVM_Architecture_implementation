package com.ujjwalsingh.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ujjwalsingh.mvvm.Data.AppRepository;
import com.ujjwalsingh.mvvm.Note.NotesEntity;

import java.util.List;

public class LiveDataViewModel extends AndroidViewModel {
    public LiveData<List<NotesEntity>> mDataList;
    private AppRepository appRepository;


    public LiveDataViewModel(@NonNull Application application) {
        super(application);
        appRepository=AppRepository.getInstance(application.getApplicationContext());
        mDataList = appRepository.mDataList;

    }

    public void appData() {
        appRepository.appData();
    }

    public void deleteAll() {
        appRepository.deleteAll();
    }
}
