package com.example.notesapp.ui.fragment;

import com.example.notesapp.repository.NoteRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class NoteListFragment_MembersInjector implements MembersInjector<NoteListFragment> {
  private final Provider<NoteRepository> repositoryProvider;

  public NoteListFragment_MembersInjector(Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  public static MembersInjector<NoteListFragment> create(
      Provider<NoteRepository> repositoryProvider) {
    return new NoteListFragment_MembersInjector(repositoryProvider);
  }

  @Override
  public void injectMembers(NoteListFragment instance) {
    injectRepository(instance, repositoryProvider.get());
  }

  @InjectedFieldSignature("com.example.notesapp.ui.fragment.NoteListFragment.repository")
  public static void injectRepository(NoteListFragment instance, NoteRepository repository) {
    instance.repository = repository;
  }
}
