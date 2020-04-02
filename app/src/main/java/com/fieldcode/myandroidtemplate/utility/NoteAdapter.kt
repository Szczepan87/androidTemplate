package com.fieldcode.myandroidtemplate.utility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fieldcode.myandroidtemplate.databinding.NoteCardBinding
import com.fieldcode.myandroidtemplate.model.Note

class NoteAdapter :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val notes: MutableList<Note> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteCardBinding.inflate(inflater)
        return NoteViewHolder(binding)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(notes[position])

    fun updateList(newList: List<Note>) {
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: NoteCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteItem: Note) {
            with(binding) {
                note = noteItem
                noteCardTitleTextView.text = noteItem.title
                noteCardContentTextView.text = noteItem.content
                executePendingBindings()
            }

        }

    }
}