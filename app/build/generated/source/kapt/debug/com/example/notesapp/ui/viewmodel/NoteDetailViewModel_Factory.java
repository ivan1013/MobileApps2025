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
public final class NoteDetailViewModel_Factory implements Factory<NoteDetailViewModel> {
  private final Provider<NoteRepository> repositoryProvider;

  public NoteDetailViewModel_Factory(Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public NoteDetailViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static NoteDetailViewModel_Factory create(Provider<NoteRepository> repositoryProvider) {
    return new NoteDetailViewModel_Factory(repositoryProvider);
  }

  public static NoteDetailViewModel newInstance(NoteRepository repository) {
    return new NoteDetailViewModel(repository);
  }
}
