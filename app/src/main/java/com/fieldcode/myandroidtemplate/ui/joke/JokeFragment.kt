package com.fieldcode.myandroidtemplate.ui.joke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.koin.android.ext.android.get

import com.fieldcode.myandroidtemplate.R
import com.fieldcode.myandroidtemplate.databinding.FragmentJokeBinding

class JokeFragment : Fragment() {

    private lateinit var binding: FragmentJokeBinding
    private val viewModel: JokeViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_joke, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel = this@JokeFragment.viewModel
            lifecycleOwner = this@JokeFragment.viewLifecycleOwner
            GlobalScope.launch { viewModel?.getJoke() }
            viewModel?.joke?.observe(
                viewLifecycleOwner,
                Observer { jokeContent.text = it.joke })
        }
    }
}
