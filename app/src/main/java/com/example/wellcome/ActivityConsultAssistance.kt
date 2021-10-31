package com.example.wellcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.wellcome.utils.db.AppDatabase
import kotlinx.android.synthetic.main.activity_consult_assistance.*

class ActivityConsultAssistance : AppCompatActivity() {
    val db = Room.databaseBuilder(
        this,
        AppDatabase::class.java, "wellcome"
    ).fallbackToDestructiveMigration().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult_assistance)
        val bundle = intent.extras
        recycler_view_c_page_assistance.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=ConsultAssistanceAdapter(context,
                db.assistanceDao().findAssistanceById(bundle?.getInt("id").toString()))
        }
    }
}