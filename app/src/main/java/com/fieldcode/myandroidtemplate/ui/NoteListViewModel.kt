package com.fieldcode.myandroidtemplate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fieldcode.myandroidtemplate.model.Note
import com.fieldcode.myandroidtemplate.repository.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteListViewModel(private val noteDao: NoteDao) : ViewModel() {
    lateinit var provideDatabaseData: Job
    val notesList = MutableLiveData<MutableList<Note>>(provideList())
    val addNewNoteLiveData = MutableLiveData<Boolean>(false)

    private fun provideList(): MutableList<Note> {
        var listFromDatabase = mutableListOf<Note>()
        provideDatabaseData = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                listFromDatabase = noteDao.getAll().toMutableList()
            }
        }
        return listFromDatabase
    }
}