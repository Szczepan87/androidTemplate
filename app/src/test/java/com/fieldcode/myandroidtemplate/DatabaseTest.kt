package com.fieldcode.myandroidtemplate

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.fieldcode.myandroidtemplate.model.Note
import com.fieldcode.myandroidtemplate.repository.NoteDao
import com.fieldcode.myandroidtemplate.repository.NoteDatabase
import com.fieldcode.myandroidtemplate.ui.MainActivity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    private lateinit var noteDao: NoteDao
    private lateinit var db: NoteDatabase
    private val testNote = Note(0, Date(), "Note", "Note content")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, NoteDatabase::class.java
        ).build()
        noteDao = db.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertToDatabase() = runBlocking {
        noteDao.insertNote(testNote)
        assertEquals(testNote, noteDao.getByTitle(testNote.title))
    }

    @Test
    fun removeFromDatabase() {

    }
}