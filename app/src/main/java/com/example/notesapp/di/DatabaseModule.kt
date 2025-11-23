package com.example.notesapp.di

import android.content.Context
import androidx.room.Room
import com.example.notesapp.data.local.AppDatabase
import com.example.notesapp.data.local.NoteDao
import com.example.notesapp.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "notes_database"
    ).build()
    
    @Singleton
    @Provides
    fun provideNoteDao(database: AppDatabase): NoteDao = database.noteDao()
    
    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepository(noteDao)
}
