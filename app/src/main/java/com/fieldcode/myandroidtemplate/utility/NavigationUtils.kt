package com.fieldcode.myandroidtemplate.utility

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

inline fun <reified T : Activity> Context.startActivity() =
    Intent(this, T::class.java).run { startActivity(this) }

fun Fragment.navigateTo(@IdRes destination: Int) =
    findNavController().navigate(destination)

fun Fragment.navigateBack() = findNavController().popBackStack()