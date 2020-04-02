package com.fieldcode.myandroidtemplate.binding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.fieldcode.myandroidtemplate.utility.empty
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

object DateBindingAdapter {

    @SuppressLint("ConstantLocale")
    private val desiredDateFormatWithHour =
        SimpleDateFormat("dd.MM.yyyy - HH:mm", Locale.getDefault())

    @JvmStatic
    @BindingAdapter("noteCreationDate")
    fun TextView.ticketCreationDate(unformattedDate: Date) = try {
        text = desiredDateFormatWithHour.format(unformattedDate)
    } catch (exception: ParseException) {
        text = String.empty
    }
}