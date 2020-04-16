package com.fieldcode.myandroidtemplate.utility

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import java.net.InetAddress
import java.net.UnknownHostException

class InternetConnectionChecker(application: Application) {

    private val connectivityManager =
        application.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isConnected(): MutableLiveData<Boolean> {
        val isConnectivityAvailable = connectivityManager.activeNetworkInfo?.isConnected ?: false
        val isInternetAvailable = isInternetAvailable()
        return MutableLiveData<Boolean>(isConnectivityAvailable && isInternetAvailable)
    }

    private fun isInternetAvailable(): Boolean {
        try {
            val address: InetAddress = InetAddress.getByName("www.google.com")
            return !address.equals("")
        } catch (e: UnknownHostException) {
            // Log error
        }
        return false
    }
}