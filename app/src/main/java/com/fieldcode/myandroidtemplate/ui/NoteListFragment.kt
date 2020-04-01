package com.fieldcode.myandroidtemplate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.koin.android.viewmodel.ext.android.getViewModel
import com.fieldcode.myandroidtemplate.R
import com.fieldcode.myandroidtemplate.databinding.NotesListFragmentBinding
import com.fieldcode.myandroidtemplate.utility.NoteAdapter
import com.fieldcode.myandroidtemplate.utility.navigateTo


class NoteListFragment : Fragment() {

    private lateinit var viewModel: NoteListViewModel
    private lateinit var binding: NotesListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.notes_list_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel()
        val listOfNotes = viewModel.notesList.value
        val adapter = NoteAdapter(listOfNotes ?: listOf())
        with(binding) {
            lifecycleOwner = this@NoteListFragment
            viewModel = this@NoteListFragment.viewModel
            noteListRecyclerView.adapter = adapter
            noteListFAB.setOnClickListener { navigateTo(R.id.action_notesListFragment_to_createNoteFragment) }
        }
    }
}
