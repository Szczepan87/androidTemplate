package com.fieldcode.myandroidtemplate.utility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fieldcode.myandroidtemplate.databinding.NoteCardBinding
import com.fieldcode.myandroidtemplate.model.Note

class NoteAdapter(private val notes: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteCardBinding.inflate(inflater)
        return NoteViewHolder(binding)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(notes[position])

    inner class NoteViewHolder(val binding: NoteCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                noteCardTitleTextView.text = note.title
                noteCardContentTextView.text = note.content
            }

        }

    }
}