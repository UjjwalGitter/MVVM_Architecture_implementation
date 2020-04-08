package com.ujjwalsingh.mvvm.Note;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "notes_table")
public class NotesEntity {
    @PrimaryKey(autoGenerate = true)
    int id;
    String text;
    Date date;

    public NotesEntity(int id,Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }
@Ignore
    public NotesEntity(Date date,String text) {
        this.date = date;
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
