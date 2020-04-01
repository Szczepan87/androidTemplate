package com.fieldcode.myandroidtemplate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fieldcode.myandroidtemplate.model.Note
import com.fieldcode.myandroidtemplate.repository.NoteDao
import com.fieldcode.myandroidtemplate.utility.empty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class CreateNoteViewModel(private val noteDAO: NoteDao) : ViewModel() {

    val noteTitle = MutableLiveData(String.empty)
    val noteContent = MutableLiveData(String.empty)
    lateinit var saveNoteJob: Job

    fun saveNewNote() {
        val note = Note(
            0,
            Date(),
            title = noteTitle.value ?: String.empty,
            content = noteContent.value ?: String.empty
        )

        saveNoteJob = viewModelScope.launch { saveNote(note) }
    }

    private suspend fun saveNote(note: Note) {
        withContext(Dispatchers.IO) { noteDAO.insertNote(note) }
    }
}
