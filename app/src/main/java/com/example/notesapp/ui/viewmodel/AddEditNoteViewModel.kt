package com.example.notesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.local.NoteEntity
import com.example.notesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    
    private val _saveSuccess = MutableLiveData<Boolean>()
    val saveSuccess: LiveData<Boolean> = _saveSuccess
    
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    private val _noteData = MutableLiveData<NoteEntity>()
    val noteData: LiveData<NoteEntity> = _noteData
    
    fun loadNote(noteId: Int) {
        viewModelScope.launch {
            try {
                val note = repository.getNoteById(noteId)
                if (note != null) {
                    _noteData.value = note
                } else {
                    _errorMessage.value = "Note not found"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to load note"
            }
        }
    }
    
    fun saveNote(title: String, content: String, category: String, noteId: Int? = null) {
        if (title.isBlank() || content.isBlank()) {
            _errorMessage.value = "Title and content cannot be empty"
            _saveSuccess.value = false
            return
        }
        
        viewModelScope.launch {
            try {
                val note = if (noteId != null && noteId > 0) {
                    // Update existing note
                    NoteEntity(
                        id = noteId,
                        title = title,
                        content = content,
                        category = category,
                        createdDate = _noteData.value?.createdDate ?: System.currentTimeMillis(),
                        lastModified = System.currentTimeMillis()
                    )
                } else {
                    // Create new note
                    NoteEntity(
                        title = title,
                        content = content,
                        category = category,
                        createdDate = System.currentTimeMillis(),
                        lastModified = System.currentTimeMillis()
                    )
                }
                
                if (noteId != null && noteId > 0) {
                    repository.updateNote(note)
                } else {
                    repository.insertNote(note)
                }
                
                _saveSuccess.value = true
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to save note"
                _saveSuccess.value = false
            }
        }
    }
}
