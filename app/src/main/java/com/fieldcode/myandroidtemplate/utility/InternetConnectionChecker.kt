package com.fieldcode.myandroidtemplate.utility

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData

class InternetConnectionChecker(application: Application) {

    private val connectivityManager =
        application.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isConnected(): MutableLiveData<Boolean> {
        val isConnectivityAvailable = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting ?: false
        return MutableLiveData<Boolean>(isConnectivityAvailable)
    }
}