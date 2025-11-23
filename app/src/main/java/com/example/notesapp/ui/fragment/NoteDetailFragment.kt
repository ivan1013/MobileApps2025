package com.example.notesapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNoteDetailBinding
import com.example.notesapp.ui.viewmodel.NoteDetailViewModel
import com.example.notesapp.util.DateTimeUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailFragment : Fragment() {
    
    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteDetailViewModel by viewModels()
    private var noteId: Int = -1
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        noteId = arguments?.getInt("noteId") ?: -1
        if (noteId > 0) {
            viewModel.loadNote(noteId)
        }
        
        setupButtons()
        observeViewModel()
    }
    
    private fun setupButtons() {
        binding.editButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_noteDetailFragment_to_addEditNoteFragment,
                Bundle().apply { putInt("noteId", noteId) }
            )
        }
        
        binding.deleteButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Delete Note")
                .setMessage(R.string.delete_confirmation)
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .setPositiveButton("Delete") { _, _ ->
                    viewModel.deleteNote()
                }
                .show()
        }
        
        binding.shareButton.setOnClickListener {
            val note = viewModel.note.value
            if (note != null) {
                shareNote(note.title, note.content)
            }
        }
    }
    
    private fun shareNote(title: String, content: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, content)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share Note"))
    }
    
    private fun observeViewModel() {
        viewModel.note.observe(viewLifecycleOwner) { note ->
            binding.apply {
                titleTextView.text = note.title
                categoryTextView.text = "Category: ${note.category}"
                createdDateTextView.text = "Created: ${DateTimeUtil.formatDateTime(note.createdDate)}"
                lastModifiedTextView.text = "Last Modified: ${DateTimeUtil.formatDateTime(note.lastModified)}"
                contentTextView.text = note.content
            }
        }
        
        viewModel.deleteSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(requireContext(), "Note deleted successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_noteDetailFragment_to_noteListFragment)
            }
        }
        
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
