package com.fieldcode.myandroidtemplate

import android.app.Application
import com.fieldcode.myandroidtemplate.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                appModule,
                databaseModule,
                viewModelModule,
                netModule,
                internetConnectionCheckerModule
            )
        }
    }
}