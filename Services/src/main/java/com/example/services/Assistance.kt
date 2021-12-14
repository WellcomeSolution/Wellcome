package com.example.services

class Assistance(title: String, description: String, address: Address, phone: String, tags: List<String>, val priority: Priority) :
    Service(title, description, address,phone, tags)
