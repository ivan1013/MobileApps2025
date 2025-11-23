package com.example.notesapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.notesapp.data.local.NoteEntity
import com.example.notesapp.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class AddEditNoteViewModelTest {
    
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    private lateinit var repository: NoteRepository
    private lateinit var observer: Observer<Boolean>
    private lateinit var errorObserver: Observer<String>
    private lateinit var viewModel: AddEditNoteViewModel
    
    @Before
    fun setup() {
        repository = mock()
        observer = mock()
        errorObserver = mock()
        viewModel = AddEditNoteViewModel(repository)
    }
    
    @Test
    fun saveNewNoteSuccess() = runTest {
        doReturn(1L).whenever(repository).insertNote(any())
        
        viewModel.saveSuccess.observeForever(observer)
        viewModel.saveNote("Test", "Content", "General", null)
        
        // Wait for coroutines to complete
        advanceUntilIdle()
        
        verify(observer).onChanged(true)
    }
    
    @Test
    fun saveNoteEmptyTitleFails() = runTest {
        viewModel.saveSuccess.observeForever(observer)
        viewModel.errorMessage.observeForever(errorObserver)
        
        viewModel.saveNote("", "Content", "General", null)
        
        // Empty title should fail immediately (synchronous check)
        verify(observer).onChanged(false)
        verify(errorObserver).onChanged("Title and content cannot be empty")
    }
    
    @Test
    fun saveNoteEmptyContentFails() = runTest {
        viewModel.saveSuccess.observeForever(observer)
        viewModel.errorMessage.observeForever(errorObserver)
        
        viewModel.saveNote("Title", "", "General", null)
        
        // Empty content should fail immediately
        verify(observer).onChanged(false)
        verify(errorObserver).onChanged("Title and content cannot be empty")
    }
    
    @Test
    fun updateNoteSuccess() = runTest {
        val noteId = 1
        val existingNote = NoteEntity(
            id = noteId,
            title = "Original",
            content = "Original Content",
            category = "General"
        )
        
        // Mock loading existing note
        doReturn(existingNote).whenever(repository).getNoteById(noteId)
        
        // Mock update
        doNothing().whenever(repository).updateNote(any())
        
        viewModel.saveSuccess.observeForever(observer)
        viewModel.loadNote(noteId)
        advanceUntilIdle()
        
        viewModel.saveNote("Updated", "New Content", "Updated", noteId)
        advanceUntilIdle()
        
        verify(observer).onChanged(true)
    }
}

