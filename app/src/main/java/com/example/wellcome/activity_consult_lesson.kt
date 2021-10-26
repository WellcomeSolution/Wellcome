package com.example.wellcome

import android.os.Bundle
import android.text.SpannableString
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.*
import com.example.wellcome.utils.WellcomeDbContext
import kotlinx.android.synthetic.main.activity_consult_lesson.*


class activity_consult_lesson: AppCompatActivity() {
    val dbContext: WellcomeDbContext = WellcomeDbContext(this@activity_consult_lesson)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.wellcome.R.layout.activity_consult_lesson)
       val bundle = intent.extras
        val phoneNumber = bundle?.getString("phone")
        val pn : String = phoneNumber.toString()
        recycler_view_page.apply {
            layoutManager= LinearLayoutManager(this@activity_consult_lesson)
            adapter=ConsultLessonAdapter(this@activity_consult_lesson,dbContext.searchLessonByPhone(pn))
        }
        //Toast.makeText(this,dbContext.searchLessonByPhone(pn)[0].toString(),Toast.LENGTH_SHORT).show()

       /*val lesson = com.example.wellcome.models.Lesson(
            dbContext.searchLessonByPhone(pn).title,
            dbContext.searchLessonByPhone(pn).description,
            dbContext.searchLessonByPhone(pn).address,
            dbContext.searchLessonByPhone(pn).phone,
            dbContext.searchLessonByPhone(pn).tags,
            dbContext.searchLessonByPhone(pn).sessionDuration
        )
        title_cours_con.text = lesson.title
        phone_cours_con.text = lesson.phone
        address_cours_con.text = lesson.address
        services_description_con.setText(lesson.description)
        if(lesson.tags.toString().contains(checkbox1_cours.text)){
            checkbox1_cours.isChecked
        }
        if(lesson.tags.toString().contains(checkbox2_cours.text)){
            checkbox2_cours.isChecked
        }
        if(lesson.tags.toString().contains(checkbox3_cours.text)){
            checkbox3_cours.isChecked
        }
        cours_sessionduree.setText(lesson.sessionDuration)*/
    }
}