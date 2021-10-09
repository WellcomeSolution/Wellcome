package com.example.wellcome.models

class Assistance(title: String, description: String, address: String, tags: List<String>, priority: Priority) :
    Service(title, description, address, tags)
{

}