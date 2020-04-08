package com.fieldcode.myandroidtemplate.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.fieldcode.myandroidtemplate.repository.NoteDatabase
import com.fieldcode.myandroidtemplate.repository.NoteRepository
import com.fieldcode.myandroidtemplate.ui.CreateNoteViewModel
import com.fieldcode.myandroidtemplate.ui.NoteListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
    }
}

val viewModelModule = module {
    viewModel { CreateNoteViewModel(get()) }
    viewModel { NoteListViewModel(get()) }
}

val databaseModule = module {
    single { provideNoteDAO(androidContext()) }
    single { NoteRepository(get()) }
}

private fun provideNoteDAO(context: Context) = Room.databaseBuilder(
    context,
    NoteDatabase::class.java,
    "note-database"
).fallbackToDestructiveMigration()
    .build().noteDao()