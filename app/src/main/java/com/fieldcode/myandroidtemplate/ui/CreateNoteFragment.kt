package com.fieldcode.myandroidtemplate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fieldcode.myandroidtemplate.R
import com.fieldcode.myandroidtemplate.databinding.CreateNoteFragmentBinding
import com.fieldcode.myandroidtemplate.utility.navigateBack
import org.koin.android.ext.android.get

class CreateNoteFragment : Fragment() {

    private val viewModel: CreateNoteViewModel = get()
    private lateinit var binding: CreateNoteFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CreateNoteFragment.viewModel
            setUpAddingFab()
        }
    }

    private fun CreateNoteFragmentBinding.setUpAddingFab() {
        noteConfirmFAB.setOnClickListener {
            viewModel?.saveNewNote()
            navigateBack()
        }
    }
}
