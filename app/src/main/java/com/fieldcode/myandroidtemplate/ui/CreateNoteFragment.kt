package com.fieldcode.myandroidtemplate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fieldcode.myandroidtemplate.R
import com.fieldcode.myandroidtemplate.databinding.CreateNoteFragmentBinding
import com.fieldcode.myandroidtemplate.model.Note
import com.fieldcode.myandroidtemplate.utility.empty
import com.fieldcode.myandroidtemplate.utility.navigateBack
import org.koin.android.ext.android.get
import java.util.*

class CreateNoteFragment : Fragment() {

    private val viewModel: CreateNoteViewModel = get()
    private lateinit var binding: CreateNoteFragmentBinding
    private var noteToEdit: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().title = getString(R.string.create_note)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.create_note_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val note = arguments?.getParcelable<Note>(NoteListFragment.NOTE_KEY)
        noteToEdit = note ?: DEFAULT_NOTE
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CreateNoteFragment.viewModel
            setUpAddingFab()
            if (noteToEdit != null) {
                viewModel?.noteTitle?.value = noteToEdit?.title ?: String.empty
                viewModel?.noteContent?.value = noteToEdit?.content ?: String.empty
            }
        }
    }

    private fun CreateNoteFragmentBinding.setUpAddingFab() {
        noteConfirmFAB.setOnClickListener {
            viewModel?.saveNewNote()
            navigateBack()
        }
    }

    companion object {
        private val DEFAULT_NOTE = Note(0, Date(), String.empty, String.empty)
    }
}
