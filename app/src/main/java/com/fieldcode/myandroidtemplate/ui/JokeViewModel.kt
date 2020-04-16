package com.fieldcode.myandroidtemplate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fieldcode.myandroidtemplate.model.Joke
import com.fieldcode.myandroidtemplate.repository.JokeRepository
import com.fieldcode.myandroidtemplate.utility.InternetConnectionChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val internetConnectionChecker: InternetConnectionChecker by inject(
        InternetConnectionChecker::class.java
    )

    val joke = MutableLiveData<Joke>()

    fun getJoke() {
        if (internetConnectionChecker.isConnected().value == true) {
            scope.launch {
                val remoteJoke = jokeRepository.getJoke()?.value?.first()
                joke.postValue(remoteJoke)
            }
        } else joke.postValue(OFFLINE_JOKE)
    }

    companion object {
        private val OFFLINE_JOKE = Joke(0, "No joke without internet connection", listOf())
    }
}