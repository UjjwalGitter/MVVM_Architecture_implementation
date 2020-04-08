package com.ujjwalsingh.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ujjwalsingh.mvvm.Note.NotesEntity;
import com.ujjwalsingh.mvvm.viewmodel.EditorViewModel;

public class EditorActivity extends AppCompatActivity {

    private EditorViewModel editorViewModel;
    EditText editText;
    Button updateBT;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        editText = findViewById(R.id.edit_text);
        updateBT = findViewById(R.id.update);
        Log.i("Greatos",editText.getText().toString());
        intent= getIntent();
        initModel();
        int kp = intent.getIntExtra("node",100);
        updateBTClicked();
        Log.i("GREA", String.valueOf(kp));
    }

    private void updateBTClicked() {
        updateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(editText.getText().toString());
                finish();
            }
        });
    }

    private void initModel() {
        Log.i("openr","DSfd");
        editorViewModel = ViewModelProviders.of(this).get(EditorViewModel.class);
        Log.i("krates","DSfd");
        editorViewModel.liveNote.observe(this, new Observer<NotesEntity>() {
            @Override
            public void onChanged(NotesEntity notesEntity) {
                if (editorViewModel !=null) {
                    editText.setText(notesEntity.getText().toString());
                }
            }
        });
        Log.i("opeter","DSfd");
        Bundle bundle =getIntent().getExtras();
        if (bundle!=null) {
            int kp = intent.getIntExtra("node",2);
            Log.i("Greako",String.valueOf(kp));
            int noteId = bundle.getInt("node");
            Log.i("GREA", String.valueOf(noteId));
            editorViewModel.loadNote(kp);
        }
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateData(editText.getText().toString());
    }

    public void updateData(String s){
        editorViewModel.updateData(s);
        }
    }

