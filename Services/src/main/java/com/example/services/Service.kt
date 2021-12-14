package com.example.services

abstract class Service(val title: String, val description: String, val address: Address, val phone: String, val tags: List<String>) {
    fun isTagExist(tag: String) : Boolean = tags.contains(tag)
    fun isTagsExist(tags : List<String>) : Boolean = this.tags.intersect(tags).isNotEmpty()
    //abstract fun isTagsExist(tags : List<String>) : Boolean
}