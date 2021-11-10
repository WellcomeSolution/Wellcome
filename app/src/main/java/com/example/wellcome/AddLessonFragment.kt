package com.example.wellcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wellcome.utils.db.*
import kotlinx.android.synthetic.main.fragment_add_lesson.*


class AddLessonFragment: BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_lesson, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        suivantButton.setOnClickListener {

            db.lessonDao().insert(retrieveCours())
            clearTextEdit()
            Toast.makeText(context, "lesson added", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearTextEdit() {
        services_titre.text?.clear()
        services_phone.text?.clear()
        lesson_address_1.text?.clear()
        lesson_country.text?.clear()
        lesson_city.text?.clear()
        lesson_department.text?.clear()
        lesson_postalcode.text?.clear()
        services_description.text?.clear()
        cours_sessionduree.text?.clear()
        checkbox1_cours.isChecked = false
        checkbox2_cours.isChecked = false
        checkbox3_cours.isChecked = false
    }
        fun retrieveCours(): Lesson {
            var mlist = mutableListOf<String>()
            if (checkbox1_cours.isChecked) {
                mlist.add(checkbox1_cours.text.toString())
            }
            if (checkbox2_cours.isChecked) {
                mlist.add(checkbox2_cours.text.toString())
            }
            if (checkbox3_cours.isChecked) {
                mlist.add(checkbox3_cours.text.toString())
            }
            var list = mlist.toList()
            var ret_cours = Lesson(
                services_titre.text.toString(),
                services_description.text.toString(),
                getAddress(),
                services_phone.text.toString(),
                true,
                list,
                1
            )
            return ret_cours
        }
        fun getAddress(): Address
        = Address(
            country = Country(
                addressLine = lesson_country.text.toString(),
                administrativeArea = AdministrativeArea(
                    addressLine = lesson_department.text.toString(),locality = Locality(
                        addressLine = lesson_city.text.toString(),
                        thoroughfare = Thoroughfare(addressLine = lesson_address_1.text.toString()),
                        postalCode = PostalCode(addressLine = lesson_postalcode.text.toString())
                    )
                )
            )
        )
    }
