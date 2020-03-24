package com.fieldcode.myandroidtemplate.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fieldcode.myandroidtemplate.utility.empty
import java.util.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "date") val date: Date = Date(),
    @ColumnInfo(name = "title") val title: String = String.empty,
    @ColumnInfo(name = "content") val content: String = String.empty
)