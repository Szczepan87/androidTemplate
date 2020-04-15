package com.fieldcode.myandroidtemplate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fieldcode.myandroidtemplate.model.Joke
import com.fieldcode.myandroidtemplate.repository.JokeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val joke = MutableLiveData<Joke>()

    fun getJoke() {
        scope.launch {
            val remoteJoke = jokeRepository.getJoke()?.value?.first()
            joke.postValue(remoteJoke)
        }
    }
}