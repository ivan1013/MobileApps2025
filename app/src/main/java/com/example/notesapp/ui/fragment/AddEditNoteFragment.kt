package com.example.notesapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentAddEditNoteBinding
import com.example.notesapp.ui.viewmodel.AddEditNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditNoteFragment : Fragment() {
    
    private var _binding: FragmentAddEditNoteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddEditNoteViewModel by viewModels()
    private var noteId: Int = -1
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        noteId = arguments?.getInt("noteId") ?: -1
        
        setupTextFields()
        setupButtons()
        observeViewModel()
        
        // Load existing note if editing
        if (noteId > 0) {
            viewModel.loadNote(noteId)
        }
    }
    
    private fun setupTextFields() {
        // Ensure all text fields accept Unicode characters including Bulgarian Cyrillic
        // Remove ALL filters and set the most permissive input type
        
        // Title field - accept all text
        binding.titleEditText.filters = arrayOf() // Remove all filters
        binding.titleEditText.inputType = android.text.InputType.TYPE_CLASS_TEXT
        binding.titleEditText.setRawInputType(android.text.InputType.TYPE_CLASS_TEXT)
        
        // Category field - accept all text
        binding.categoryEditText.filters = arrayOf() // Remove all filters
        binding.categoryEditText.inputType = android.text.InputType.TYPE_CLASS_TEXT
        binding.categoryEditText.setRawInputType(android.text.InputType.TYPE_CLASS_TEXT)
        
        // Content field - multiline, accept all text
        binding.contentEditText.filters = arrayOf() // Remove all filters
        binding.contentEditText.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE
        binding.contentEditText.setRawInputType(android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE)
    }
    
    private fun setupButtons() {
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val category = binding.categoryEditText.text.toString().ifEmpty { "General" }
            
            viewModel.saveNote(title, content, category, if (noteId > 0) noteId else null)
        }
        
        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    
    private fun observeViewModel() {
        viewModel.noteData.observe(viewLifecycleOwner) { note ->
            // Pre-populate form with existing note data
            binding.titleEditText.setText(note.title)
            binding.contentEditText.setText(note.content)
            binding.categoryEditText.setText(note.category)
        }
        
        viewModel.saveSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(requireContext(), "Note saved successfully", Toast.LENGTH_SHORT).show()
                // Add a small delay to ensure database is committed
                binding.root.postDelayed({
                    findNavController().navigate(R.id.action_addEditNoteFragment_to_noteListFragment)
                }, 300)
            }
        }
        
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
