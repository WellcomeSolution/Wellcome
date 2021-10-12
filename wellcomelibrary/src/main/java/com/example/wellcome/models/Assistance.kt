package com.example.wellcome.models

class Assistance(title: String, description: String, address: String,phone: String, tags: List<String>, val priority: String) :
    Service(title, description, address,phone, tags)
{

}