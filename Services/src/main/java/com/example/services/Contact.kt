package com.example.services

import kotlinx.serialization.Serializable

@Serializable
class Contact(
    val firstName:String,
    val lastName:String,
    val phone:String,
    val mail:String)
