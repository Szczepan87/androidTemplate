package com.fieldcode.myandroidtemplate.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fieldcode.myandroidtemplate.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE uid = :noteId")
    fun getById(noteId: Int): Note

    @Query("SELECT * FROM note WHERE title LIKE :title")
    fun getByTitle(title: String): Note

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun delete(note: Note)
}