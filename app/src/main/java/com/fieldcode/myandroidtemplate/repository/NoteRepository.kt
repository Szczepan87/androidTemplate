package com.fieldcode.myandroidtemplate.repository

import androidx.lifecycle.LiveData
import com.fieldcode.myandroidtemplate.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao: NoteDao) {

    val notesList = noteDao.getAll()

    suspend fun getById(noteId: Int): Note? {
        var note: Note? = null
        withContext(Dispatchers.IO) { note = noteDao.getById(noteId) }
        return note
    }

    suspend fun getByTitle(title: String): LiveData<List<Note>>? {
        var list: LiveData<List<Note>>? = null
        withContext(Dispatchers.IO) { list = noteDao.getByTitle(title) }
        return list
    }

    suspend fun insertNote(note: Note) = withContext(Dispatchers.IO) { noteDao.insertNote(note) }

    suspend fun delete(note: Note) = withContext(Dispatchers.IO) { noteDao.delete(note) }
}