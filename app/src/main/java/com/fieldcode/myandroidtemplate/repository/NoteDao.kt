package com.fieldcode.myandroidtemplate.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fieldcode.myandroidtemplate.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE uid = :noteId")
    fun getById(noteId: Int): Note

    @Query("SELECT * FROM note WHERE title LIKE :title")
    fun getByTitle(title: String): LiveData<List<Note>>

    @Query("SELECT * FROM note ORDER BY date")
    fun getAllByDateDSC(): LiveData<List<Note>>

    @Query("SELECT * FROM note ORDER BY date ASC")
    fun getAllByDateASC(): LiveData<List<Note>>

    @Query("SELECT * FROM note ORDER BY title")
    fun getAllAlphabetically(): LiveData<List<Note>>

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun delete(note: Note)
}