package com.example.wellcome.utils

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns

class WellcomeDbContext(context:Context) {
    val dpHelper = WellcomeDbHelper(context)

    /*fun insertHosting(hosting: com.example.wellcome.models.Hosting): Long? {
        val db = dpHelper.writableDatabase

        val values = ContentValues()
        values.put(HostingContract.HostingEntry.COLUMN_NAME_FIRST_NAME, hosting.firstName)
        values.put(HostingContract.HostingEntry.COLUMN_NAME_LAST_NAME, hosting.lastName)
        values.put(HostingContract.HostingEntry.COLUMN_NAME_ADDRESS, hosting.address)
        values.put(HostingContract.HostingEntry.COLUMN_NAME_EMAIL, hosting.email)
        values.put(HostingContract.HostingEntry.COLUMN_NAME_PHONE, hosting.phone)

        return db?.insert(HostingContract.HostingEntry.TABLE_NAME, null, values)
    }

    fun insertAssistance(assistance: com.example.wellcome.models.Assistance): Long? {
        val db = dpHelper.writableDatabase
        var tags_string: String = assistance.tags.joinToString()
        val values = ContentValues()
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_TITRE_SERVICE, assistance.title)
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_DESCRIPTION, assistance.description)
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_ADDRESS, assistance.address)
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_TELEPHONE, assistance.phone)
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_TAGS, tags_string)
        values.put(Assistance.AssistanceEntry.COLUMN_NAME_PRIORITY, assistance.priority)

        return db?.insert(Assistance.AssistanceEntry.TABLE_NAME, null, values)
    }

    fun insertCours(cours: com.example.wellcome.models.Lesson): Long? {
        val db = dpHelper.writableDatabase
        var tags_string: String = cours.tags.joinToString()
        val values = ContentValues()
        values.put(Cours.CoursEntry.COLUMN_NAME_TITRE_SERVICE, cours.title)
        values.put(Cours.CoursEntry.COLUMN_NAME_DESCRIPTION, cours.description)
        values.put(Cours.CoursEntry.COLUMN_NAME_ADDRESS, cours.address)
        values.put(Cours.CoursEntry.COLUMN_NAME_TELEPHONE, cours.phone)
        values.put(Cours.CoursEntry.COLUMN_NAME_TAGS, tags_string)
        values.put(Cours.CoursEntry.COLUMN_NAME_DURATION, cours.sessionDuration)

        return db?.insert(Cours.CoursEntry.TABLE_NAME, null, values)
    }

    fun insertLogement(logement: com.example.wellcome.models.Host): Long? {
        val db = dpHelper.writableDatabase
        var tags_string: String = logement.tags.joinToString()
        val values = ContentValues()
        values.put(Logement.LogementEntry.COLUMN_NAME_TITRE_SERVICE, logement.title)
        values.put(Logement.LogementEntry.COLUMN_NAME_DESCRIPTION, logement.description)
        values.put(Logement.LogementEntry.COLUMN_NAME_ADDRESS, logement.address)
        values.put(Logement.LogementEntry.COLUMN_NAME_TELEPHONE, logement.phone)
        values.put(Logement.LogementEntry.COLUMN_NAME_TAGS, tags_string)
        values.put(Logement.LogementEntry.COLUMN_NAME_PERSONE, logement.maxPeople)
        values.put(Logement.LogementEntry.COLUMN_NAME_PIECE, logement.maxRoom)


        return db?.insert(Logement.LogementEntry.TABLE_NAME, null, values)
    }

    fun getHostings(): List<com.example.wellcome.models.Hosting> {
        val db = dpHelper.readableDatabase
        val sortOrder = "${HostingContract.HostingEntry.COLUMN_NAME_FIRST_NAME} DESC"

        val cursor = db.query(
            HostingContract.HostingEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        val hostings = arrayListOf<com.example.wellcome.models.Hosting>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val firstName =
                    getString(getColumnIndexOrThrow(HostingContract.HostingEntry.COLUMN_NAME_FIRST_NAME))
                val lastName =
                    getString(getColumnIndexOrThrow(HostingContract.HostingEntry.COLUMN_NAME_LAST_NAME))
                val address =
                    getString(getColumnIndexOrThrow(HostingContract.HostingEntry.COLUMN_NAME_ADDRESS))
                val email =
                    getString(getColumnIndexOrThrow(HostingContract.HostingEntry.COLUMN_NAME_EMAIL))
                val phone =
                    getString(getColumnIndexOrThrow(HostingContract.HostingEntry.COLUMN_NAME_PHONE))

                val hosting = com.example.wellcome.models.Hosting(
                    id,
                    firstName,
                    lastName,
                    address,
                    email,
                    phone
                )
                hostings.add(hosting)
            }
        }
        cursor.close()
        return hostings
    }

    fun getHosts(): List<com.example.wellcome.models.Host> {
        val db = dpHelper.readableDatabase
        val sortOrder = "${Logement.LogementEntry.COLUMN_NAME_TITRE_SERVICE} DESC"

        val cursor = db.query(
            Logement.LogementEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        val hosts = arrayListOf<com.example.wellcome.models.Host>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val title =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_TITRE_SERVICE))
                val description =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_DESCRIPTION))
                val address =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_ADDRESS))
                val phone =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_TELEPHONE))
                val tags = getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_TAGS))
                val nombrePersonne =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_PERSONE))
                val nombrePiece =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_PIECE))
                var mlist = mutableListOf<String>()
                var count = 0
                for (x in tags) {
                    if (x == ',') {
                        count = 1
                    }
                }
                if (count == 1) {
                    mlist = tags.split(',').toMutableList()
                } else {
                    mlist.add(tags)
                }
                var listtags = mlist.toList()
                val host = com.example.wellcome.models.Host(
                    title,
                    description,
                    address,
                    phone,
                    listtags,
                    nombrePersonne,
                    nombrePiece
                )
                hosts.add(host)
            }
        }
        cursor.close()
        return hosts
    }

    fun getAssistances(): List<com.example.wellcome.models.Assistance> {
        val db = dpHelper.readableDatabase
        val sortOrder = "${Assistance.AssistanceEntry.COLUMN_NAME_TITRE_SERVICE} DESC"

        val cursor = db.query(
            Assistance.AssistanceEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        val assistances = arrayListOf<com.example.wellcome.models.Assistance>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val title =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_TITRE_SERVICE))
                val description =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_DESCRIPTION))
                val address =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_ADDRESS))
                val phone =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_TELEPHONE))
                val tags =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_TAGS))
                val priority =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_PRIORITY))
                var mlist = mutableListOf<String>()
                var count = 0
                for (x in tags) {
                    if (x == ',') {
                        count = 1
                    }
                }
                if (count == 1) {
                    mlist = tags.split(',').toMutableList()
                } else {
                    mlist.add(tags)
                }
                var listtags = mlist.toList()
                val assistance = com.example.wellcome.models.Assistance(
                    title,
                    description,
                    address,
                    phone,
                    listtags,
                    priority
                )
                assistances.add(assistance)
            }
        }
        cursor.close()
        return assistances
    }

    fun getCours(): List<com.example.wellcome.models.Lesson> {
        val db = dpHelper.readableDatabase
        val sortOrder = "${Cours.CoursEntry.COLUMN_NAME_TITRE_SERVICE} DESC"

        val cursor = db.query(
            Cours.CoursEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        val Courss = arrayListOf<com.example.wellcome.models.Lesson>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val title =
                    getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_TITRE_SERVICE))
                val description =
                    getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_DESCRIPTION))
                val address = getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_ADDRESS))
                val phone = getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_TELEPHONE))
                val tags = getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_TAGS))
                val duration =
                    getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_DURATION))
                var mlist = mutableListOf<String>()
                var count = 0
                for (x in tags) {
                    if (x == ',') {
                        count = 1
                    }
                }
                if (count == 1) {
                    mlist = tags.split(',').toMutableList()
                } else {
                    mlist.add(tags)
                }
                var listtags = mlist.toList()
                val cours = com.example.wellcome.models.Lesson(
                    title,
                    description,
                    address,
                    phone,
                    listtags,
                    duration
                )
                Courss.add(cours)
            }
        }
        cursor.close()
        return Courss
    }
    fun searchCoursByTags(tagsv : List<String>): List<com.example.wellcome.models.Lesson> {
        val db = dpHelper.readableDatabase
        val sortOrder = "${Cours.CoursEntry.COLUMN_NAME_TITRE_SERVICE} DESC"

        val cursor = db.query(
            Cours.CoursEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        val Courss = arrayListOf<com.example.wellcome.models.Lesson>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val title =
                    getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_TITRE_SERVICE))
                val description =
                    getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_DESCRIPTION))
                val address = getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_ADDRESS))
                val phone = getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_TELEPHONE))
                val tags = getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_TAGS))
                val duration =
                    getString(getColumnIndexOrThrow(Cours.CoursEntry.COLUMN_NAME_DURATION))
                var mlist = mutableListOf<String>()
                var newTags = tags.replace("\\s".toRegex(), "")

                var count = 0
                for (x in newTags) {
                    if (x == ',') {
                        count = 1
                    }
                }
                if (count == 1) {
                    mlist = newTags.split(',').toMutableList()
                } else {
                    mlist.add(newTags)
                }
                var listtags = mlist.toList()
                val cours = com.example.wellcome.models.Lesson(
                    title,
                    description,
                    address,
                    phone,
                    listtags,
                    duration
                )
                if(cours.isTagsExist(tagsv)){
                    Courss.add(cours)
                }
            }
        }
        cursor.close()
        return Courss
    }
    fun searchAssistancesByTags(tagsv: List<String>): List<com.example.wellcome.models.Assistance> {
        val db = dpHelper.readableDatabase
        val sortOrder = "${Assistance.AssistanceEntry.COLUMN_NAME_TITRE_SERVICE} DESC"

        val cursor = db.query(
            Assistance.AssistanceEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        val assistances = arrayListOf<com.example.wellcome.models.Assistance>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val title =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_TITRE_SERVICE))
                val description =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_DESCRIPTION))
                val address =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_ADDRESS))
                val phone =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_TELEPHONE))
                val tags =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_TAGS))
                val priority =
                    getString(getColumnIndexOrThrow(Assistance.AssistanceEntry.COLUMN_NAME_PRIORITY))
                var mlist = mutableListOf<String>()
                var newTags = tags.replace("\\s".toRegex(), "")
                var count = 0
                for (x in newTags) {
                    if (x == ',') {
                        count = 1
                    }
                }
                if (count == 1) {
                    mlist = newTags.split(',').toMutableList()
                } else {
                    mlist.add(newTags)
                }
                var listtags = mlist.toList()
                val assistance = com.example.wellcome.models.Assistance(
                    title,
                    description,
                    address,
                    phone,
                    listtags,
                    priority
                )
                if(assistance.isTagsExist(tagsv)){
                    assistances.add(assistance)
                }
            }
        }
        cursor.close()
        return assistances
    }
    fun searchHostsByTags(tagsv: List<String>): List<com.example.wellcome.models.Host> {
        val db = dpHelper.readableDatabase
        val sortOrder = "${Logement.LogementEntry.COLUMN_NAME_TITRE_SERVICE} DESC"

        val cursor = db.query(
            Logement.LogementEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        val hosts = arrayListOf<com.example.wellcome.models.Host>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val title =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_TITRE_SERVICE))
                val description =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_DESCRIPTION))
                val address =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_ADDRESS))
                val phone =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_TELEPHONE))
                val tags = getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_TAGS))
                val nombrePersonne =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_PERSONE))
                val nombrePiece =
                    getString(getColumnIndexOrThrow(Logement.LogementEntry.COLUMN_NAME_PIECE))
                var mlist = mutableListOf<String>()
                var count = 0
                var newTags = tags.replace("\\s".toRegex(), "")
                for (x in newTags) {
                    if (x == ',') {
                        count = 1
                    }
                }
                if (count == 1) {
                    mlist = newTags.split(',').toMutableList()
                } else {
                    mlist.add(newTags)
                }
                var listtags = mlist.toList()
                val host = com.example.wellcome.models.Host(
                    title,
                    description,
                    address,
                    phone,
                    listtags,
                    nombrePersonne,
                    nombrePiece
                )
                if(host.isTagsExist(tagsv)){
                    hosts.add(host)
                }

            }
        }
        cursor.close()
        return hosts
    }
    */
}