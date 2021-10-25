package com.example.wellcome.utils.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

@Entity
data class Address(
    @Embedded val country: Country
)

@Entity
data class Country(
    @ColumnInfo(name = "countryLine")
    val addressLine: String,
    @Embedded val administrativeArea: AdministrativeArea
)

@Entity
data class AdministrativeArea(
    @ColumnInfo(name = "adminLine")
    val addressLine: String,
    @Embedded val locality: Locality
)

@Entity
data class Locality(
    @ColumnInfo(name = "localityLine")
    val addressLine: String,
    @Embedded val thoroughfare: Thoroughfare,
    @Embedded val postalCode: PostalCode
)

@Entity
data class Thoroughfare(
    @ColumnInfo(name = "thoroughfareLine")
    val addressLine: String
)

@Entity
data class PostalCode(
    @ColumnInfo(name = "postalCodeLine")
    val addressLine: String
)