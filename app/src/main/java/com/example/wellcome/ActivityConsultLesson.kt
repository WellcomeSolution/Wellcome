package com.example.wellcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import java.util.*
import com.example.wellcome.utils.db.AppDatabase
import kotlinx.android.synthetic.main.activity_consult_lesson.*


class activity_consult_lesson: AppCompatActivity() {
    val db = Room.databaseBuilder(
        this,
        AppDatabase::class.java, "wellcome"
    ).fallbackToDestructiveMigration().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult_lesson)

        val bundle = intent.extras
        val phoneNumber = bundle?.getString("phone")
        val pn : String = phoneNumber.toString()

        recycler_view_page.apply {
            layoutManager= LinearLayoutManager(this@activity_consult_lesson)
            adapter=ConsultLessonAdapter(this@activity_consult_lesson,dbContext.searchLessonByPhone(pn))
        }
    }
}