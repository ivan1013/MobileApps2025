package com.example.notesapp.ui.viewmodel;

import com.example.notesapp.repository.NoteRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AddEditNoteViewModel_Factory implements Factory<AddEditNoteViewModel> {
  private final Provider<NoteRepository> repositoryProvider;

  public AddEditNoteViewModel_Factory(Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddEditNoteViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddEditNoteViewModel_Factory create(Provider<NoteRepository> repositoryProvider) {
    return new AddEditNoteViewModel_Factory(repositoryProvider);
  }

  public static AddEditNoteViewModel newInstance(NoteRepository repository) {
    return new AddEditNoteViewModel(repository);
  }
}
