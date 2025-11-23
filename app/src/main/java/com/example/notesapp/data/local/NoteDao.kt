package com.example.notesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    
    @Insert
    suspend fun insertNote(note: NoteEntity): Long
    
    @Update
    suspend fun updateNote(note: NoteEntity)
    
    @Delete
    suspend fun deleteNote(note: NoteEntity)
    
    @Query("SELECT * FROM notes ORDER BY lastModified DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>
    
    @Query("SELECT * FROM notes WHERE id = :noteId")
    suspend fun getNoteById(noteId: Int): NoteEntity?
    
    @Query("SELECT * FROM notes WHERE title LIKE :query OR content LIKE :query ORDER BY lastModified DESC")
    fun searchNotes(query: String): Flow<List<NoteEntity>>
    
    @Query("SELECT * FROM notes WHERE category = :category ORDER BY lastModified DESC")
    fun getNotesByCategory(category: String): Flow<List<NoteEntity>>
    
    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()
}
