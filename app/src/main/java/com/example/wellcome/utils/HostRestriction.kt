package com.example.wellcome.utils

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HostRestrictions(
    val travelers :Int,
    val isBabiesAllowed :Boolean,
    val isDogsAllowed: Boolean,
    val isChildsAllowed : Boolean) {
}