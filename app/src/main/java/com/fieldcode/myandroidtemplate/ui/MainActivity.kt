package com.fieldcode.myandroidtemplate.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fieldcode.myandroidtemplate.R
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val sharedPrefs: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
