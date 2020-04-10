package com.fieldcode.myandroidtemplate.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fieldcode.myandroidtemplate.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note ORDER BY date")
    fun getAll(): LiveData<List<Note>>

    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun delete(note: Note)
}