package com.example.wellcome.models

class Lesson(title: String, description: String, address: Address,
             phone: String, tags: List<String>, val sessionDuration: Int) : Service(title, description,
    address,phone, tags)
