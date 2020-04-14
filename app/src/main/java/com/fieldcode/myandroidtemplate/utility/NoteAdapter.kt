package com.fieldcode.myandroidtemplate.utility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fieldcode.myandroidtemplate.R
import com.fieldcode.myandroidtemplate.databinding.NoteCardBinding
import com.fieldcode.myandroidtemplate.model.Note
import kotlinx.android.synthetic.main.note_card.view.*

typealias OnItemRemoved = (Note) -> (Unit)
typealias OnEditNoteListener = (Note?) -> (Unit)

class NoteAdapter :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val notes: MutableList<Note> = mutableListOf()
    var onItemRemoved: OnItemRemoved? = null
    var onEditNoteListener: OnEditNoteListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteCardBinding.inflate(inflater, parent, false)
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

    fun removeAt(position: Int) {
        onItemRemoved?.invoke(notes[position])
    }

    fun sortByTitle() {
        notes.sortBy { it.title }
        notifyDataSetChanged()
    }

    fun sortByDate() {
        notes.sortBy { it.date }
        notifyDataSetChanged()
    }

    fun sortByDateDesc() {
        notes.sortByDescending { it.date }
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
                noteCardLayout.setOnLongClickListener {
                    onEditNoteListener?.invoke(note)
                    true
                }
            }
        }
    }
}