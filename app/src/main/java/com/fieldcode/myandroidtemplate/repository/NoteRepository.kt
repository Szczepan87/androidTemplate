package com.fieldcode.myandroidtemplate.repository

import com.fieldcode.myandroidtemplate.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val noteDao: NoteDao) {

    val notesList = noteDao.getAll()

    suspend fun getById(noteId: Int) = withContext(Dispatchers.IO) { noteDao.getById(noteId) }

    suspend fun getByTitle(title: String) =
        withContext(Dispatchers.IO) { noteDao.getByTitle(title) }

    suspend fun insertNote(note: Note) = withContext(Dispatchers.IO) { noteDao.insertNote(note) }

    suspend fun delete(note: Note) = withContext(Dispatchers.IO) { noteDao.delete(note) }
}