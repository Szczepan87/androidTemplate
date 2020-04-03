package com.fieldcode.myandroidtemplate.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

object ErrorTextBindingAdapter {

    @JvmStatic
    @BindingAdapter("errorText")
    fun TextInputEditText.errorMessage(message: String?) {
        error = message
    }
}