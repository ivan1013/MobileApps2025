package com.example.notesapp.ui.fragment;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0017H\u0016J\b\u0010!\u001a\u00020\u0017H\u0016J\u001a\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010$\u001a\u00020\u0017H\u0002J\b\u0010%\u001a\u00020\u0017H\u0002J\b\u0010&\u001a\u00020\u0017H\u0002J\b\u0010\'\u001a\u00020\u0017H\u0002J\b\u0010(\u001a\u00020\u0017H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006)"}, d2 = {"Lcom/example/notesapp/ui/fragment/NoteListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/notesapp/databinding/FragmentNoteListBinding;", "binding", "getBinding", "()Lcom/example/notesapp/databinding/FragmentNoteListBinding;", "noteAdapter", "Lcom/example/notesapp/ui/adapter/NoteAdapter;", "repository", "Lcom/example/notesapp/repository/NoteRepository;", "getRepository", "()Lcom/example/notesapp/repository/NoteRepository;", "setRepository", "(Lcom/example/notesapp/repository/NoteRepository;)V", "viewModel", "Lcom/example/notesapp/ui/viewmodel/MainViewModel;", "getViewModel", "()Lcom/example/notesapp/ui/viewmodel/MainViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "observeNotes", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "view", "setupDarkModeToggle", "setupFab", "setupRecyclerView", "setupSearch", "updateDarkModeIcon", "app_debug"})
public final class NoteListFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.example.notesapp.databinding.FragmentNoteListBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.example.notesapp.ui.adapter.NoteAdapter noteAdapter;
    @javax.inject.Inject()
    public com.example.notesapp.repository.NoteRepository repository;
    
    public NoteListFragment() {
        super();
    }
    
    private final com.example.notesapp.databinding.FragmentNoteListBinding getBinding() {
        return null;
    }
    
    private final com.example.notesapp.ui.viewmodel.MainViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.notesapp.repository.NoteRepository getRepository() {
        return null;
    }
    
    public final void setRepository(@org.jetbrains.annotations.NotNull()
    com.example.notesapp.repository.NoteRepository p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void observeNotes() {
    }
    
    private final void setupFab() {
    }
    
    private final void setupSearch() {
    }
    
    private final void setupDarkModeToggle() {
    }
    
    private final void updateDarkModeIcon() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}