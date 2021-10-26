package com.example.wellcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellcome.utils.WellcomeDbContext
import kotlinx.android.synthetic.main.activity_consult_assistance.*

class ActivityConsultAssistance : AppCompatActivity() {
    val dbContext: WellcomeDbContext = WellcomeDbContext(this@ActivityConsultAssistance)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.wellcome.R.layout.activity_consult_assistance)
        val bundle = intent.extras
        val phoneNumber = bundle?.getString("phone")
        val pn : String = phoneNumber.toString()
        recycler_view_c_page_assistance.apply {
            layoutManager= LinearLayoutManager(this@ActivityConsultAssistance)
            adapter=ConsultAssistanceAdapter(this@ActivityConsultAssistance,dbContext.searchAssistancesByPhone(pn))
        }
    }
}