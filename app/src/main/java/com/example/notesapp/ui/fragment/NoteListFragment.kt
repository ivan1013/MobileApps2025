package com.example.notesapp.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNoteListBinding
import com.example.notesapp.repository.NoteRepository
import com.example.notesapp.ui.adapter.NoteAdapter
import com.example.notesapp.ui.viewmodel.MainViewModel
import com.example.notesapp.util.setOnTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteListFragment : Fragment() {
    
    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var noteAdapter: NoteAdapter
    
    @Inject
    lateinit var repository: NoteRepository
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeNotes()
        setupFab()
        setupSearch()
        setupDarkModeToggle()
        
        // Log dimensions - post to ensure layout is complete
        binding.root.post {
            val frameLayout = binding.root.findViewById<android.widget.FrameLayout>(android.R.id.content)
            val allChildren = mutableListOf<String>()
            for (i in 0 until binding.root.childCount) {
                val child = binding.root.getChildAt(i)
                allChildren.add("Child $i: ${child::class.simpleName} ${child.width}x${child.height}")
            }
            android.util.Log.d("NoteListFragment", "Fragment root: ${binding.root.width}x${binding.root.height}")
            android.util.Log.d("NoteListFragment", "Children: $allChildren")
            android.util.Log.d("NoteListFragment", "RecyclerView dimensions: ${binding.notesRecyclerView.width}x${binding.notesRecyclerView.height}")
            android.util.Log.d("NoteListFragment", "RecyclerView visibility: ${binding.notesRecyclerView.visibility}")
        }
    }
    
    override fun onResume() {
        super.onResume()
        // Refresh notes when returning to this fragment
        observeNotes()
    }
    
    private fun setupRecyclerView() {
        android.util.Log.d("NoteListFragment", "Setting up RecyclerView")
        noteAdapter = NoteAdapter(
            onNoteClick = { note ->
                findNavController().navigate(
                    R.id.action_noteListFragment_to_noteDetailFragment,
                    Bundle().apply { putInt("noteId", note.id) }
                )
            },
            onNoteDelete = { note ->
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete Note")
                    .setMessage(R.string.delete_confirmation)
                    .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                    .setPositiveButton("Delete") { _, _ ->
                        viewModel.deleteNote(note)
                    }
                    .show()
            }
        )
        binding.notesRecyclerView.apply {
            android.util.Log.d("NoteListFragment", "RecyclerView found: ${this.id}")
            adapter = noteAdapter
            layoutManager = LinearLayoutManager(requireContext())
            android.util.Log.d("NoteListFragment", "RecyclerView configured with adapter and layout manager")
        }
    }
    
    private fun observeNotes() {
        viewModel.notes.observe(viewLifecycleOwner) { notes ->
            android.util.Log.d("NoteListFragment", "Observed ${notes.size} notes, submitting to adapter")
            noteAdapter.submitList(notes)
            // Force the RecyclerView to layout items
            binding.notesRecyclerView.requestLayout()
        }
    }
    
    private fun setupFab() {
        binding.addNoteFab.setOnClickListener {
            findNavController().navigate(
                R.id.action_noteListFragment_to_addEditNoteFragment
            )
        }
    }
    
    private fun setupSearch() {
        // Ensure search field accepts Unicode characters including Bulgarian Cyrillic
        // Remove ALL filters and set the most permissive input type
        binding.searchView.filters = arrayOf() // Remove all filters
        binding.searchView.inputType = android.text.InputType.TYPE_CLASS_TEXT
        binding.searchView.setRawInputType(android.text.InputType.TYPE_CLASS_TEXT)
        
        binding.searchView.setOnTextChanged { text, _, _, _ ->
            val query = text.toString()
            if (query.isNotEmpty()) {
                repository.searchNotes(query).asLiveData().observe(viewLifecycleOwner) { results ->
                    noteAdapter.submitList(results)
                }
            } else {
                observeNotes()
            }
        }
    }
    
    private fun setupDarkModeToggle() {
        val prefs: SharedPreferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        updateDarkModeIcon()
        
        binding.darkModeToggleButton.setOnClickListener {
            val currentMode = AppCompatDelegate.getDefaultNightMode()
            val isCurrentlyDark = when (currentMode) {
                AppCompatDelegate.MODE_NIGHT_YES -> true
                AppCompatDelegate.MODE_NIGHT_NO -> false
                else -> {
                    // Check system state
                    (resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK) == android.content.res.Configuration.UI_MODE_NIGHT_YES
                }
            }
            
            val newMode = if (isCurrentlyDark) {
                AppCompatDelegate.MODE_NIGHT_NO
            } else {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            
            // Save preference first - use commit() to ensure it's saved before recreate
            val isDark = newMode == AppCompatDelegate.MODE_NIGHT_YES
            prefs.edit().putBoolean("dark_mode", isDark).commit()
            
            // Set the mode
            AppCompatDelegate.setDefaultNightMode(newMode)
            
            // Recreate activity to apply theme change immediately
            requireActivity().recreate()
        }
    }
    
    private fun updateDarkModeIcon() {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        val isDarkMode = when (currentMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> true
            AppCompatDelegate.MODE_NIGHT_NO -> false
            else -> {
                // Check system state
                (resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK) == android.content.res.Configuration.UI_MODE_NIGHT_YES
            }
        }
        
        // Use sun/moon icons - for now using available system icons
        // In a production app, you'd use Material icons like ic_brightness_6 or custom drawables
        val iconRes = if (isDarkMode) {
            android.R.drawable.ic_menu_revert
        } else {
            android.R.drawable.ic_menu_view
        }
        binding.darkModeToggleButton.setIconResource(iconRes)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
