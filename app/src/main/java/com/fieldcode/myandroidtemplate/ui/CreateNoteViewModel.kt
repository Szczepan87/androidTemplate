package com.fieldcode.myandroidtemplate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fieldcode.myandroidtemplate.model.Note
import com.fieldcode.myandroidtemplate.repository.NoteDao
import com.fieldcode.myandroidtemplate.repository.NoteRepository
import com.fieldcode.myandroidtemplate.utility.empty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class CreateNoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val noteTitle = MutableLiveData(String.empty)
    val noteContent = MutableLiveData(String.empty)
    val canSaveNote = MutableLiveData(false)

    fun saveNewNote() {
        if (areFieldsFilled()) {
            canSaveNote.value = true
            val note = Note(
                0,
                Date(),
                title = noteTitle.value ?: String.empty,
                content = noteContent.value ?: String.empty
            )
            viewModelScope.launch { saveNote(note) }
        } else canSaveNote.value = false
    }

    fun updateExistingNote(note: Note) {
        val noteToUpdate = Note(
            note.uid,
            note.date,
            noteTitle.value ?: String.empty,
            noteContent.value ?: String.empty
        )
        viewModelScope.launch(Dispatchers.IO) { updateNote(noteToUpdate) }
    }

    private suspend fun updateNote(note: Note) {
        withContext(Dispatchers.IO) { noteRepository.updateNote(note) }
    }

    private suspend fun saveNote(note: Note) {
        withContext(Dispatchers.IO) { noteRepository.insertNote(note) }
    }

    private fun areFieldsFilled() =
        noteTitle.value?.isNotEmpty() == true && noteContent.value?.isNotEmpty() == true
}
