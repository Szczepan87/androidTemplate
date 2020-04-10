package com.fieldcode.myandroidtemplate.repository

import androidx.lifecycle.LiveData
import com.fieldcode.myandroidtemplate.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao: NoteDao) {

    val notesList = noteDao.getAll()

    suspend fun insertNote(note: Note) = withContext(Dispatchers.IO) { noteDao.insertNote(note) }

    suspend fun updateNote(note: Note) = withContext(Dispatchers.IO) { noteDao.updateNote(note) }

    suspend fun delete(note: Note) = withContext(Dispatchers.IO) { noteDao.delete(note) }
}