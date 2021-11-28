package com.example.wellcome.models

import java.io.Serializable

class HostRestrictions(
    val travelers: Int?,
    val babies: Int?,
    val pets: Int?,
    val childs: Int?) : Serializable