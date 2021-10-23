package com.example.wellcome.models

class Assistance(title: String, description: String, address: Address, phone: String, tags: List<String>, val priority: Priority) :
    Service(title, description, address,phone, tags)
{

}