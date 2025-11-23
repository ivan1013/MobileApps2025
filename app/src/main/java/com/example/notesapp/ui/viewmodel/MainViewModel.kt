package com.example.notesapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.local.NoteEntity
import com.example.notesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    
    private val _notes = MutableLiveData<List<NoteEntity>>()
    val notes: LiveData<List<NoteEntity>> = _notes
    
    init {
        loadNotes()
    }
    
    private fun loadNotes() {
        viewModelScope.launch {
            repository.getAllNotes().collect { noteList ->
                _notes.value = noteList
            }
        }
    }
    
    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery
    
    val searchResults: LiveData<List<NoteEntity>> = _searchQuery.switchMap { query ->
        repository.searchNotes(query).asLiveData()
    }
    
    private val _deleteSuccess = MutableLiveData<Boolean>()
    val deleteSuccess: LiveData<Boolean> = _deleteSuccess
    
    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            try {
                repository.deleteNote(note)
                _deleteSuccess.value = true
            } catch (e: Exception) {
                _deleteSuccess.value = false
            }
        }
    }
    
    fun searchNotes(query: String) {
        _searchQuery.value = query
    }
}
