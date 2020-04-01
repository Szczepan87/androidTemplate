package com.fieldcode.myandroidtemplate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fieldcode.myandroidtemplate.R
import com.fieldcode.myandroidtemplate.databinding.CreateNoteFragmentBinding
import org.koin.android.viewmodel.ext.android.getViewModel


class CreateNoteFragment : Fragment() {

    private lateinit var viewModel: CreateNoteViewModel
    private lateinit var binding: CreateNoteFragmentBinding

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@CreateNoteFragment.viewModel
        }
        // TODO: Use the ViewModel

    }

}
