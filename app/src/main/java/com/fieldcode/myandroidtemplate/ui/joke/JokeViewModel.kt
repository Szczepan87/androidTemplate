package com.fieldcode.myandroidtemplate.ui.joke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.fieldcode.myandroidtemplate.model.Joke
import com.fieldcode.myandroidtemplate.repository.joke.JokeRepository
import com.fieldcode.myandroidtemplate.utility.InternetConnectionChecker
import kotlinx.coroutines.*

class JokeViewModel(
    private val jokeRepository: JokeRepository,
    private val internetConnectionChecker: InternetConnectionChecker
) : ViewModel() {

    private val parentJob = Job()
    private val coroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)
    private val isInternetAvailable = Observer<Boolean> {  }

    val joke = MutableLiveData<Joke>()

    fun getJoke() {
        internetConnectionChecker.isConnected().observeForever(Observer {
            if (it == true) scope.launch {
                val remoteJoke = jokeRepository.getJoke()?.value?.first()
                joke.postValue(remoteJoke)
            } else joke.postValue(OFFLINE_JOKE)
        })
//        if (internetConnectionChecker.isConnected().value == true) {
//            scope.launch {
//                val remoteJoke = jokeRepository.getJoke()?.value?.first()
//                joke.postValue(remoteJoke)
//            }
//        } else joke.postValue(OFFLINE_JOKE)
    }

    companion object {
        private val OFFLINE_JOKE = Joke(0, "No joke without internet connection", listOf())
    }
}