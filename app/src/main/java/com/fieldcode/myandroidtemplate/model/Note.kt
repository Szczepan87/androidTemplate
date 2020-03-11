package com.fieldcode.myandroidtemplate.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Note(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "content") val content: String?
)