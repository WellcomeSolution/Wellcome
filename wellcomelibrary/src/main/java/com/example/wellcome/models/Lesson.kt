package com.example.wellcome.models

class Lesson(title: String, description: String, address: String,phone: String, tags: List<String>, val sessionDuration: String) : Service(title, description,
    address,phone, tags)
{
    /*override fun isTagsExist(tags: List<String>): Boolean {
        var count = 0
        for(tb in tags){
            for(ta in this.tags){
                if(ta == tb){
                    count+=1
                }
            }
        }
        if(tags.size == count){
            return true
        }
        return false
    }*/
}