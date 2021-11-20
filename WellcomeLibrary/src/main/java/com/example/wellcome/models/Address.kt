package com.example.wellcome.models

data class Address(
    val country: Country
)

data class Country(
    val addressLine: String,
    val administrativeArea: AdministrativeArea
)

data class AdministrativeArea(
    val addressLine: String,
    val locality: Locality
)

data class Locality(
    val addressLine: String,
    val thoroughfare: Thoroughfare,
    val postalCode: PostalCode
)

data class Thoroughfare(
    val addressLine: String
)

data class PostalCode(
    val addressLine: String
)