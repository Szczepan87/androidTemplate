package com.fieldcode.myandroidtemplate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fieldcode.myandroidtemplate.model.Note
import com.fieldcode.myandroidtemplate.repository.NoteDatabase
import com.fieldcode.myandroidtemplate.utility.empty
import java.util.Date

class CreateNoteViewModel(private val database: NoteDatabase) : ViewModel() {

    val noteTitle = MutableLiveData(String.empty)
    val noteContent = MutableLiveData(String.empty)
    val noteDate = MutableLiveData(Date())

    fun saveNewNote() {
        val note = Note(
            0,
            noteDate.value ?: Date(),
            noteTitle.value ?: String.empty,
            noteContent.value ?: String.empty
        )
        database.noteDao().insertNote(note)
    }
}
