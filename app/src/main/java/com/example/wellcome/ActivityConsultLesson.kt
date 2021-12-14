package com.example.wellcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.wellcome.db.AppDatabase
import kotlinx.android.synthetic.main.activity_consult_lesson.*


class ActivityConsultLesson: AppCompatActivity() {
    val db = Room.databaseBuilder(
        this,
        AppDatabase::class.java, "wellcome"
    ).fallbackToDestructiveMigration().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult_lesson)

        val bundle = intent.extras

        recycler_view_page.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=ConsultLessonAdapter(context, db.lessonDao().findLessonById(bundle?.getInt("id").toString()))
        }
    }
}