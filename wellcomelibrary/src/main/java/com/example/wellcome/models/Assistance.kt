package com.example.wellcome.models

class Assistance(title: String, description: String, address: String,phone: String, tags: List<String>, val priority: String) :
    Service(title, description, address,phone, tags)
{
    override fun isTagsExist(tagsv: List<String>): Boolean {
        var count = 0
       for(tb in tagsv){
           for(ta in this.tags){
               if(ta == tb){
                   count+=1
               }
           }
       }
        if(tagsv.size == count){
            return true
        }
        return false
    }
}