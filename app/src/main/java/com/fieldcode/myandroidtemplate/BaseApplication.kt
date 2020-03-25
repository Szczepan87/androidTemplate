package com.fieldcode.myandroidtemplate

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.fieldcode.myandroidtemplate.di.appModule
import com.fieldcode.myandroidtemplate.di.databaseModule
import com.fieldcode.myandroidtemplate.di.viewModelModule
import com.fieldcode.myandroidtemplate.repository.NoteDatabase
import com.fieldcode.myandroidtemplate.ui.CreateNoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(appModule, viewModelModule, databaseModule)
        }
    }
}