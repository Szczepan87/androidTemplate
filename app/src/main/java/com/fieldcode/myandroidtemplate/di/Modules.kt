package com.fieldcode.myandroidtemplate.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.fieldcode.myandroidtemplate.repository.NoteDatabase
import com.fieldcode.myandroidtemplate.ui.CreateNoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

