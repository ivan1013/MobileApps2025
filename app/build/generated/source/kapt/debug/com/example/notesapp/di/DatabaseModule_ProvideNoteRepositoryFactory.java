package com.example.notesapp.di;

import com.example.notesapp.data.local.NoteDao;
import com.example.notesapp.repository.NoteRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DatabaseModule_ProvideNoteRepositoryFactory implements Factory<NoteRepository> {
  private final Provider<NoteDao> noteDaoProvider;

  public DatabaseModule_ProvideNoteRepositoryFactory(Provider<NoteDao> noteDaoProvider) {
    this.noteDaoProvider = noteDaoProvider;
  }

  @Override
  public NoteRepository get() {
    return provideNoteRepository(noteDaoProvider.get());
  }

  public static DatabaseModule_ProvideNoteRepositoryFactory create(
      Provider<NoteDao> noteDaoProvider) {
    return new DatabaseModule_ProvideNoteRepositoryFactory(noteDaoProvider);
  }

  public static NoteRepository provideNoteRepository(NoteDao noteDao) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideNoteRepository(noteDao));
  }
}
