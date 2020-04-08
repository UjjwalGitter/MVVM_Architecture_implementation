package com.ujjwalsingh.mvvm.Note;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NotesEntity notesEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NotesEntity> notesEntity);

    @Delete
    void deleteNote(NotesEntity notesEntity);

    @Query("Select * from notes_table where id=:id")
    NotesEntity getNoteById(int id);

    @Query("Select * from notes_table ORDER BY date Desc")
    LiveData<List<NotesEntity>> getAllNote();

    @Query("delete from notes_table")
    int deleteAllNotes();

    @Query("Select COUNT(*) from notes_table")
    int getTotalCounts();

    @Query("SELECT * FROM notes_table")
    LiveData<List<NotesEntity>> selectAllUsers();


}
