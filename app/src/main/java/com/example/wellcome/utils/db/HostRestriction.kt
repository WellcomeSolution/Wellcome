package com.example.wellcome.utils.db

import android.provider.BaseColumns
import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class HostRestrictions(
    var travelers :Int = 0,
    var childs: Int,
    var pets: Int,
    var babies: Int)