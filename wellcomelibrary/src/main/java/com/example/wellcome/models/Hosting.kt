package com.example.wellcome.models

class Hosting(id:Long, lastName: String, firstName: String, address: String, email: String, phone: String) {
    val lastName = lastName
    val firstName = firstName
    val address = address
    val email = email
    val phone = phone

    constructor(lastName: String, firstName: String, address: String, email: String, phone: String):this(-1, lastName, firstName, address, email, phone)
}