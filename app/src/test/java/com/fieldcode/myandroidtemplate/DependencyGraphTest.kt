package com.fieldcode.myandroidtemplate

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.KoinContextHandler.get
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock

class DependencyGraphTest : KoinTest {

    val mockModule = module {
        single<SharedPreferences> {
            androidContext().getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        }
    }

    val sharedPrefs: SharedPreferences by inject()

    @Before
    fun before() {
        startKoin {
            androidContext(ApplicationProvider.getApplicationContext())
            modules(mockModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `should inject dependency`() {
        assertNotNull(sharedPrefs)
    }

    @Test
    fun `should add to shared prefs`() {
        sharedPrefs.edit().putInt("0", 0)
        val result = sharedPrefs.getInt("0", Int.MAX_VALUE)
        assertEquals(0, result)
    }
}