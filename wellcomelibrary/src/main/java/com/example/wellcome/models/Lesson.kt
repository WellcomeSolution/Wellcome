package com.example.wellcome.models

class Lesson(title: String, description: String, address: Address,
             phone: String, tags: List<String>, val sessionDuration: String) : Service(title, description,
    address,phone, tags)
