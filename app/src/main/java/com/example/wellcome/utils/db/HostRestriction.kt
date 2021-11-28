package com.example.wellcome.utils.db

import androidx.room.Entity

@Entity
data class HostRestrictions(
    var travelers: Int = 0,
    var childs: Int,
    var pets: Int,
    var babies: Int)