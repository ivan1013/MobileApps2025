package com.example.notesapp.repository

import com.example.notesapp.data.local.NoteDao
import com.example.notesapp.data.local.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    
    fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAllNotes()
    
    suspend fun getNoteById(noteId: Int): NoteEntity? = noteDao.getNoteById(noteId)
    
    suspend fun insertNote(note: NoteEntity): Long = noteDao.insertNote(note)
    
    suspend fun updateNote(note: NoteEntity) = noteDao.updateNote(note)
    
    suspend fun deleteNote(note: NoteEntity) = noteDao.deleteNote(note)
    
    fun searchNotes(query: String): Flow<List<NoteEntity>> {
        val searchQuery = "%$query%"
        return noteDao.searchNotes(searchQuery)
    }
    
    fun getNotesByCategory(category: String): Flow<List<NoteEntity>> = 
        noteDao.getNotesByCategory(category)
    
    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()
}
