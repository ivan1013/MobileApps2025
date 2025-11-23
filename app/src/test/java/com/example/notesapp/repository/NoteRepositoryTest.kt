package com.example.notesapp.repository

import com.example.notesapp.data.local.NoteDao
import com.example.notesapp.data.local.NoteEntity
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NoteRepositoryTest {
    
    private lateinit var noteDao: NoteDao
    private lateinit var noteRepository: NoteRepository
    
    @Before
    fun setup() {
        noteDao = mock()
        noteRepository = NoteRepository(noteDao)
    }
    
    @Test
    fun insertNote() = runBlocking {
        val note = NoteEntity(
            title = "Test Note",
            content = "Test content"
        )
        doReturn(1L).whenever(noteDao).insertNote(note)
        
        val result = noteRepository.insertNote(note)
        
        assert(result == 1L)
        verify(noteDao).insertNote(note)
    }
    
    @Test
    fun updateNote() = runBlocking {
        val note = NoteEntity(
            id = 1,
            title = "Updated Note",
            content = "Updated content"
        )
        
        noteRepository.updateNote(note)
        
        verify(noteDao).updateNote(note)
    }
    
    @Test
    fun deleteNote() = runBlocking {
        val note = NoteEntity(
            id = 1,
            title = "Test Note",
            content = "Test content"
        )
        
        noteRepository.deleteNote(note)
        
        verify(noteDao).deleteNote(note)
    }
    
    @Test
    fun getAllNotes() {
        val notes = listOf(
            NoteEntity(id = 1, title = "Note 1", content = "Content 1"),
            NoteEntity(id = 2, title = "Note 2", content = "Content 2")
        )
        doReturn(flowOf(notes)).whenever(noteDao).getAllNotes()
        
        val result = noteRepository.getAllNotes()
        
        verify(noteDao).getAllNotes()
    }
    
    @Test
    fun getNoteById() = runBlocking {
        val note = NoteEntity(
            id = 1,
            title = "Test Note",
            content = "Test content"
        )
        doReturn(note).whenever(noteDao).getNoteById(1)
        
        val result = noteRepository.getNoteById(1)
        
        assert(result?.id == 1)
        verify(noteDao).getNoteById(1)
    }
    
    @Test
    fun searchNotes() {
        val query = "test"
        val notes = listOf(
            NoteEntity(id = 1, title = "Test Note", content = "Test content")
        )
        doReturn(flowOf(notes)).whenever(noteDao).searchNotes("%$query%")
        
        val result = noteRepository.searchNotes(query)
        
        verify(noteDao).searchNotes("%$query%")
    }
}

