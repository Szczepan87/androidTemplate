package com.fieldcode.myandroidtemplate

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.fieldcode.myandroidtemplate.repository.NoteDatabase
import com.fieldcode.myandroidtemplate.ui.CreateNoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {
    val appModule = module {
        single<SharedPreferences> {
            androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        }
    }

    val viewModelModule = module {
        viewModel { CreateNoteViewModel(get()) }
    }

    val databaseModule = module {
        single {
            provideDatabase(androidContext())
        }
    }

    private fun provideDatabase(context: Context) = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "note-database"
    ).fallbackToDestructiveMigration()
        .build()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(appModule, viewModelModule, databaseModule)
        }
    }
}