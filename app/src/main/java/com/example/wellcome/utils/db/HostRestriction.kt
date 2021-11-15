package com.example.wellcome.utils.db

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HostRestrictions(
    val travelers :Int = 0,
    val childs: Int,
    val pets: Int,
    val babies: Int)