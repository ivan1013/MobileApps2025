package com.example.notesapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notesapp.data.local.AppDatabase
import com.example.notesapp.data.local.NoteEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDaoTest {
    
    private lateinit var database: AppDatabase
    
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }
    
    @After
    fun tearDown() {
        database.close()
    }
    
    @Test
    fun insertNote() = runBlocking {
        val note = NoteEntity(
            title = "Test",
            content = "Content"
        )
        database.noteDao().insertNote(note)
        
        val notes = mutableListOf<NoteEntity>()
        database.noteDao().getAllNotes().collect { notes.addAll(it) }
        
        assert(notes.size == 1)
        assert(notes[0].title == "Test")
    }
    
    @Test
    fun updateNote() = runBlocking {
        val note = NoteEntity(
            title = "Original",
            content = "Content"
        )
        val insertedId = database.noteDao().insertNote(note)
        
        val updated = note.copy(id = insertedId.toInt(), title = "Updated")
        database.noteDao().updateNote(updated)
        
        val retrieved = database.noteDao().getNoteById(insertedId.toInt())
        assert(retrieved?.title == "Updated")
    }
    
    @Test
    fun deleteNote() = runBlocking {
        val note = NoteEntity(
            title = "Test",
            content = "Content"
        )
        database.noteDao().insertNote(note)
        database.noteDao().deleteNote(note)
        
        val notes = mutableListOf<NoteEntity>()
        database.noteDao().getAllNotes().collect { notes.addAll(it) }
        
        assert(notes.isEmpty())
    }
}
