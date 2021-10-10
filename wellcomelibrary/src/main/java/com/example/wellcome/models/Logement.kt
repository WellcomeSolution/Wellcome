package com.example.wellcome.models

class Logement(title: String, description: String, address: String,phone: String, tags: List<String>, nombrePersonne: String,nombrePiece:String) : Service(title, description,
    address,phone, tags)
{
    val title = title
    val description = description
    val address = address
    val phone = phone
    val tags = tags
    val nombrePersonne = nombrePersonne
    val nombrePiece = nombrePiece
}