package com.example.services

class Lesson(title: String, description: String, address: Address,
             phone: String, tags: List<String>, val sessionDuration: Int) : Service(title, description,
    address,phone, tags)
