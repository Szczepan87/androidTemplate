package com.fieldcode.myandroidtemplate.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fieldcode.myandroidtemplate.model.Note
import com.fieldcode.myandroidtemplate.repository.NoteDatabase
import com.fieldcode.myandroidtemplate.utility.empty
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
        GlobalScope.launch { database.noteDao().insertNote(note) }
        // todo move to note lists
        GlobalScope.launch {
            Log.d(
                "DATABASE:\n",
                "${database.noteDao().getAll().size }}"
            )
        }
    }
}
