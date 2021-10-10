package com.example.wellcome.models

class Assistance(title: String, description: String, address: String,phone: Int, tags: List<String>, priority: Priority) :
    Service(title, description, address,phone, tags)
{

}