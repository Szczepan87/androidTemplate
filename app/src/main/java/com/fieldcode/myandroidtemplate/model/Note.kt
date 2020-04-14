package com.fieldcode.myandroidtemplate.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fieldcode.myandroidtemplate.utility.empty
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "date") val date: Date = Date(),
    @ColumnInfo(name = "title") var title: String = String.empty,
    @ColumnInfo(name = "content") var content: String = String.empty
) : Parcelable