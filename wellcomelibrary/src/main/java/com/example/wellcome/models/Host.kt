package com.example.wellcome.models

class Host(title: String, description: String, address: String, phone: String, tags: List<String>, val nombrePersonne: String, val nombrePiece:String) : Service(title, description,
    address,phone, tags)
{
    /*
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
    }*/
}