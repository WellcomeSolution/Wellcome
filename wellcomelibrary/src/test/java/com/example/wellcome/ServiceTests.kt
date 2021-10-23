package com.example.wellcome

import com.example.wellcome.models.*
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class ServiceTests {
    @Test
    fun serviceContainsTagTest(){
        var assistance = Assistance("title", "description", getHostAddress(), "phone", listOf("babe", "handicape"), "1")
        val result = assistance.isTagExist("babe")
        assertTrue(result)
    }

    @Test
    fun serviceContainsTagsTest(){
        var assistance = Assistance("title", "description", getHostAddress(), "phone", listOf("babe", "handicape"), "1")
        val result = assistance.isTagsExist(listOf("babe", "handicape"))
        assertTrue(result)
    }

    @Test
    fun serviceNotContainsTagTest(){
        var assistance = Assistance("title", "description", getHostAddress(), "phone", listOf("babe", "handicape"), "1")
        val result = assistance.isTagExist("random")
        assertFalse(result)
    }

    @Test
    fun serviceNotContainsTagsTest(){
        var assistance = Assistance("title", "description", getHostAddress(), "phone", listOf("babe", "handicape"), "1")
        val result = assistance.isTagsExist(listOf("random", "random2"))
        assertFalse(result)
    }

    @Test
    fun serviceContainsAnyTagsTest(){
        var assistance = Assistance("title", "description", getHostAddress(), "phone", listOf("babe", "handicape"), "1")
        val result = assistance.isTagsExist(listOf("babe", "random2"))
        assertTrue(result)
    }

    private fun getHostAddress() : Address
            = Address(country = Country(
        addressLine = "France",
        administrativeArea = AdministrativeArea(
            addressLine = "Val d'oise", locality = Locality(
                addressLine = "Soisy",
                thoroughfare = Thoroughfare(addressLine = "9 rue du puits grenet"),
                postalCode = PostalCode(addressLine = "95230")
            )
        )
    )
    )
}