package com.fieldcode.myandroidtemplate.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fieldcode.myandroidtemplate.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE uid = :noteId")
    suspend fun getById(noteId: Int): Note

    @Query("SELECT * FROM note WHERE title LIKE :title")
    suspend fun getByTitle(title: String): List<Note>

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun delete(note: Note)
}