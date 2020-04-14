package com.fieldcode.myandroidtemplate.utility

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

inline fun <reified T : Activity> Context.startActivity() =
    Intent(this, T::class.java).run { startActivity(this) }

fun Fragment.navigateTo(@IdRes destination: Int, bundle: Bundle? = null) =
    findNavController().navigate(destination, bundle)

fun Fragment.navigateBack() = findNavController().popBackStack()