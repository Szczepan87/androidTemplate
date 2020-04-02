package com.fieldcode.myandroidtemplate.ui

import androidx.lifecycle.ViewModel
import com.fieldcode.myandroidtemplate.model.Note
import com.fieldcode.myandroidtemplate.repository.NoteDao
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class NoteListViewModel(private val noteDao: NoteDao) : ViewModel() {

    suspend fun provideDataFromDatabase(): List<Note> {
        return withContext(Dispatchers.IO) {
            noteDao.getAll()
        }
    }
}