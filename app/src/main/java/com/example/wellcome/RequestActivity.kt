package com.example.wellcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.wellcome.utils.db.AppDatabase
import com.example.wellcome.utils.db.AssistanceRequest
import com.example.wellcome.utils.db.TripRequest
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_assistance_request.*
import kotlinx.android.synthetic.main.activity_request.*
import kotlinx.android.synthetic.main.activity_request.editText_message
import kotlinx.android.synthetic.main.activity_request.editText_travelers
import kotlinx.android.synthetic.main.activity_request.request_button
import kotlinx.android.synthetic.main.activity_request.request_name
import kotlinx.android.synthetic.main.activity_request.request_phone
import kotlinx.android.synthetic.main.fragement_add_trip.*
import java.time.LocalDate
import java.util.*

class RequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java, "wellcome"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select dates")
                .build()

        var startDate : Date = Date()
        var endDate : Date = Date()

        dateRangePicker.addOnPositiveButtonClickListener {
            val selectedDates = dateRangePicker.selection

            if (selectedDates != null) {
                startDate = Date(selectedDates.first)
            }
            if (selectedDates != null) {
                endDate = Date(selectedDates.second)
            }
        }

        request_button.setOnClickListener{
            var request = AssistanceRequest(startDate.toString(), endDate.toString(),
                editText_message.text.toString(),
                request_phone.text.toString(),
                request_name.text.toString()
            )
            db.requestAssistanceDao().insert(request)
            finish()
        }
    }
}