package com.example.wellcome.models

abstract class Service(val title: String, val description: String, val address: String, val phone: String,val tags: List<String>) {

    fun isTagExist(tag: String) : Boolean = tags.contains(tag)

    //fun isTagsExist(tags : List<String>) : Boolean = this.tags.intersect(tags).isNotEmpty()
    abstract fun isTagsExist(tags : List<String>) : Boolean
}