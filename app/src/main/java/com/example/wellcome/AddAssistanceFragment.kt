package com.example.wellcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import com.example.wellcome.models.Assistance
import com.example.wellcome.models.Lesson
import com.example.wellcome.models.Host
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragement_add_assistance.*



class AddAssistanceFragment: BaseFragment()  {
    var assistancePriority = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragement_add_assistance, container, false)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        assistance_add_button.setOnClickListener{

            dbContext.insertAssistance(retrieveAssistance())
            clearTextEdit()
            Toast.makeText(context,"Assistance added",Toast.LENGTH_SHORT).show()
        }
        assistance_priority.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                assistancePriority = assistance_priority.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                assistance_priority.setSelection(0)
            }

        }

    }
    private fun clearTextEdit(){
        assistance_titre.text?.clear()
        assistance_phone.text?.clear()
        assistance_address.text?.clear()
        assistance_description.text?.clear()
        checkbox1_assistance.isChecked = false
        checkbox2_assistance.isChecked = false
        checkbox3_assistance.isChecked = false

    }

    private fun retrieveAssistance(): Assistance{
        var mlist = mutableListOf<String>()
        if(checkbox1_assistance.isChecked){
            mlist.add(checkbox1_assistance.text.toString())
        }
        if(checkbox2_assistance.isChecked){
            mlist.add(checkbox2_assistance.text.toString())
        }
        if(checkbox3_assistance.isChecked){
            mlist.add(checkbox3_assistance.text.toString())
        }
        var list = mlist.toList()
        var ret_assistance = Assistance(assistance_titre.text.toString(),assistance_description.text.toString(),assistance_address.text.toString(),
            assistance_phone.text.toString(),list,assistancePriority)
        return ret_assistance
    }
}