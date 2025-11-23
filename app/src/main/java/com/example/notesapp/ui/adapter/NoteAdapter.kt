package com.example.notesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.local.NoteEntity
import com.example.notesapp.databinding.ItemNoteBinding
import com.example.notesapp.util.DateTimeUtil

class NoteAdapter(
    private val onNoteClick: (NoteEntity) -> Unit,
    private val onNoteDelete: (NoteEntity) -> Unit
) : ListAdapter<NoteEntity, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        android.util.Log.d("NoteAdapter", "Creating ViewHolder, parent size: ${parent.width}x${parent.height}")
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        android.util.Log.d("NoteAdapter", "ViewHolder root size: ${binding.root.width}x${binding.root.height}")
        return NoteViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        android.util.Log.d("NoteAdapter", "Binding item at position $position: ${getItem(position).title}")
        holder.bind(getItem(position))
    }
    
    override fun getItemCount(): Int {
        val count = super.getItemCount()
        android.util.Log.d("NoteAdapter", "getItemCount called: $count")
        return count
    }
    
    inner class NoteViewHolder(private val binding: ItemNoteBinding) : 
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(note: NoteEntity) {
            binding.apply {
                titleTextView.text = note.title
                contentPreviewTextView.text = note.content.take(100)
                dateTextView.text = DateTimeUtil.formatDate(note.lastModified)
                categoryTextView.text = note.category
                
                root.setOnClickListener {
                    onNoteClick(note)
                }
                
                deleteButton.setOnClickListener {
                    onNoteDelete(note)
                }
            }
        }
    }
    
    private class NoteDiffCallback : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity) = 
            oldItem.id == newItem.id
        
        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity) = 
            oldItem == newItem
    }
}
