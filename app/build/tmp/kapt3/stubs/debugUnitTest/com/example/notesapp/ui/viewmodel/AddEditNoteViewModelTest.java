package com.example.notesapp.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012H\u0007J\f\u0010\u0013\u001a\u00060\u0011j\u0002`\u0012H\u0007J\f\u0010\u0014\u001a\u00060\u0011j\u0002`\u0012H\u0007J\b\u0010\u0015\u001a\u00020\u0011H\u0007J\f\u0010\u0016\u001a\u00060\u0011j\u0002`\u0012H\u0007R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00020\u00078G\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/example/notesapp/ui/viewmodel/AddEditNoteViewModelTest;", "", "()V", "errorObserver", "Landroidx/lifecycle/Observer;", "", "instantTaskExecutorRule", "Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "getInstantTaskExecutorRule", "()Landroidx/arch/core/executor/testing/InstantTaskExecutorRule;", "observer", "", "repository", "Lcom/example/notesapp/repository/NoteRepository;", "viewModel", "Lcom/example/notesapp/ui/viewmodel/AddEditNoteViewModel;", "saveNewNoteSuccess", "", "Lkotlinx/coroutines/test/TestResult;", "saveNoteEmptyContentFails", "saveNoteEmptyTitleFails", "setup", "updateNoteSuccess", "app_debugUnitTest"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
public final class AddEditNoteViewModelTest {
    @org.jetbrains.annotations.NotNull()
    private final androidx.arch.core.executor.testing.InstantTaskExecutorRule instantTaskExecutorRule = null;
    private com.example.notesapp.repository.NoteRepository repository;
    private androidx.lifecycle.Observer<java.lang.Boolean> observer;
    private androidx.lifecycle.Observer<java.lang.String> errorObserver;
    private com.example.notesapp.ui.viewmodel.AddEditNoteViewModel viewModel;
    
    public AddEditNoteViewModelTest() {
        super();
    }
    
    @org.junit.Rule()
    @org.jetbrains.annotations.NotNull()
    public final androidx.arch.core.executor.testing.InstantTaskExecutorRule getInstantTaskExecutorRule() {
        return null;
    }
    
    @org.junit.Before()
    public final void setup() {
    }
    
    @org.junit.Test()
    public final void saveNewNoteSuccess() {
    }
    
    @org.junit.Test()
    public final void saveNoteEmptyTitleFails() {
    }
    
    @org.junit.Test()
    public final void saveNoteEmptyContentFails() {
    }
    
    @org.junit.Test()
    public final void updateNoteSuccess() {
    }
}