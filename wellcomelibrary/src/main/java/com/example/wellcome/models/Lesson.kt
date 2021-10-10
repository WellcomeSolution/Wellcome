package com.example.wellcome.models

class Lesson(title: String, description: String, address: String,phone: String, tags: List<String>, sessionDuration: String) : Service(title, description,
    address,phone, tags)
{
    val title = title
    val description = description
    val address = address
    val phone = phone
    val tags = tags
    val sessionDuration = sessionDuration
}