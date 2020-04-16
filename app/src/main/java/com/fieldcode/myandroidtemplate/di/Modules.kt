package com.fieldcode.myandroidtemplate.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.fieldcode.myandroidtemplate.repository.JokeRepository
import com.fieldcode.myandroidtemplate.repository.JokeService
import com.fieldcode.myandroidtemplate.repository.NoteDatabase
import com.fieldcode.myandroidtemplate.repository.NoteRepository
import com.fieldcode.myandroidtemplate.ui.CreateNoteViewModel
import com.fieldcode.myandroidtemplate.ui.JokeViewModel
import com.fieldcode.myandroidtemplate.ui.NoteListViewModel
import com.fieldcode.myandroidtemplate.utility.InternetConnectionChecker
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
    }
}

val viewModelModule = module {
    viewModel { CreateNoteViewModel(get()) }
    viewModel { NoteListViewModel(get()) }
    viewModel { JokeViewModel(get()) }
}

val databaseModule = module {
    single { provideNoteDAO(androidContext()) }
    single { NoteRepository(get()) }
}

val netModule = module {
    single { provideRetrofit() }
    single { JokeRepository(get()) }
}

val internetConnectionCheckerModule = module {
    single { InternetConnectionChecker(get()) }
}

private fun provideNoteDAO(context: Context) = Room.databaseBuilder(
    context,
    NoteDatabase::class.java,
    "note-database"
).fallbackToDestructiveMigration()
    .build().noteDao()

private fun provideRetrofit() =
    Retrofit.Builder()
        .addConverterFactory(
            GsonConverterFactory
                .create(
                    Gson()
                        .newBuilder()
                        .create()
                )
        )
        .baseUrl("https://api.icndb.com/jokes/")
        .client(
            OkHttpClient()
                .newBuilder()
                .build()
        )
        .build()
        .create(JokeService::class.java)