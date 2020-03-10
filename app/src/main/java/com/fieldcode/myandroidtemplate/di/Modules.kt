package com.fieldcode.myandroidtemplate.di

import android.content.Context
import android.content.SharedPreferences
import com.fieldcode.myandroidtemplate.ui.CreateNoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
    }
    viewModel { CreateNoteViewModel() }
}