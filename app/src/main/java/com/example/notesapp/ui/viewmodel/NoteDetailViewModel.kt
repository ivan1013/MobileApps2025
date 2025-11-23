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
class NoteDetailViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    
    private val _note = MutableLiveData<NoteEntity>()
    val note: LiveData<NoteEntity> = _note
    
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    
    private val _deleteSuccess = MutableLiveData<Boolean>()
    val deleteSuccess: LiveData<Boolean> = _deleteSuccess
    
    fun loadNote(noteId: Int) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val loadedNote = repository.getNoteById(noteId)
                if (loadedNote != null) {
                    _note.value = loadedNote
                } else {
                    _error.value = "Note not found"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load note"
            } finally {
                _loading.value = false
            }
        }
    }
    
    fun deleteNote() {
        val currentNote = _note.value ?: return
        viewModelScope.launch {
            try {
                repository.deleteNote(currentNote)
                _deleteSuccess.value = true
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to delete note"
            }
        }
    }
}
