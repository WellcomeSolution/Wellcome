package com.example.wellcome.models

class Assistance(title: String, description: String, address: String,phone: String, tags: List<String>, priority: String) :
    Service(title, description, address,phone, tags)
{
    val title = title
    val description = description
    val address = address
    val phone = phone
    val tags = tags
    val priority = priority
}