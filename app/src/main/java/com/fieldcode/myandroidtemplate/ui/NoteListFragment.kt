package com.fieldcode.myandroidtemplate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.fieldcode.myandroidtemplate.R
import com.fieldcode.myandroidtemplate.databinding.NotesListFragmentBinding
import com.fieldcode.myandroidtemplate.model.Note
import com.fieldcode.myandroidtemplate.utility.NoteAdapter
import com.fieldcode.myandroidtemplate.utility.SwipeToDeleteCallback
import com.fieldcode.myandroidtemplate.utility.navigateTo
import org.koin.android.ext.android.get

class NoteListFragment : Fragment() {

    private lateinit var swipeHandler: SwipeToDeleteCallback
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var binding: NotesListFragmentBinding
    private val adapter = NoteAdapter()
    private val viewModel: NoteListViewModel = get()

    private val noteListObserver = Observer<List<Note>> { noteList ->
        noteList.let { adapter.updateList(noteList) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        itemTouchHelper = ItemTouchHelper(swipeHandler)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.notes_list_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        with(binding) {
            lifecycleOwner = this@NoteListFragment
            viewModel = this@NoteListFragment.viewModel
            noteListRecyclerView.adapter = adapter
            itemTouchHelper.attachToRecyclerView(noteListRecyclerView)
            (noteListRecyclerView.adapter as NoteAdapter).onItemRemoved =
                { it -> viewModel?.deleteFromDatabase(it) }
            noteListFAB.setOnClickListener { navigateTo(R.id.action_notesListFragment_to_createNoteFragment) }
        }
    }

    private fun setUpRecyclerView() {
        binding.noteListRecyclerView.adapter = adapter
        viewModel.notesList.observe(viewLifecycleOwner, noteListObserver)
    }
}
