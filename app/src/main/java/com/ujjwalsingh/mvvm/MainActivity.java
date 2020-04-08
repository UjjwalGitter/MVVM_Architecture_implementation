package com.ujjwalsingh.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ujjwalsingh.mvvm.Data.AppRepository;
import com.ujjwalsingh.mvvm.Note.NoteAdapter;
import com.ujjwalsingh.mvvm.Note.NotesEntity;
import com.ujjwalsingh.mvvm.viewmodel.LiveDataViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    Button buttonAdd;
    private List<NotesEntity> list = new ArrayList<>();
    private LiveDataViewModel liveDataViewModel;
    private AppRepository appRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recy);
        buttonAdd = findViewById(R.id.buttonAdd);
        initViewModel();
        initRecycler();
    }

//    private void addNotebutton() {
//        buttonAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),EditorActivity.class);
//                intent.putExtra("name","ADD");
//                startActivity(intent);
//            }
//        });
//    }

    private void initViewModel() {
        Observer<List<NotesEntity>> observer = new Observer<List<NotesEntity>>() {
            @Override
            public void onChanged(List<NotesEntity> notesEntities) {
                list.clear();
                list.addAll(notesEntities);
                if (noteAdapter == null) {
                    noteAdapter = new NoteAdapter(MainActivity.this, list);
                    recyclerView.setAdapter(noteAdapter);
                }
                else
                    noteAdapter.notifyDataSetChanged();
            }

        };
        liveDataViewModel = ViewModelProviders.of(this).get(LiveDataViewModel.class);
        liveDataViewModel.mDataList.observe(MainActivity.this,observer);
    }

    public void initRecycler() {
        recyclerView.hasFixedSize();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.add_data:{
                addData();
                Toast.makeText(this, String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
            return true;}
                case R.id.delete_all: {
                    deleteAll();
                    Toast.makeText(this, String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteAll() {
        liveDataViewModel.deleteAll();
    }

    private void addData() {
        liveDataViewModel.appData();
    }
}
