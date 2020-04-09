package com.fieldcode.myandroidtemplate.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fieldcode.myandroidtemplate.model.Note
import com.fieldcode.myandroidtemplate.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteListViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    var notesList = noteRepository.notesList

    val sortAlphabetically = noteRepository.notesListSortByTitle

    val sortByDateDescending = noteRepository.notesListSortByDateDSC

    val sortByDateAscending = noteRepository.notesListSortByDateASC

    fun getNotesByTitle(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            notesList = noteRepository.getByTitle(title) ?: noteRepository.notesList
        }
    }

    fun deleteFromDatabase(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.delete(note)
        }
    }

}