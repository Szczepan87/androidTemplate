package com.fieldcode.myandroidtemplate.repository

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun fromLongToDate(time:Long) = Date(time)

    @TypeConverter
    fun fromDateToLong(date: Date) = date.time
}