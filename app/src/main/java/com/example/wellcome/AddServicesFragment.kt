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
import kotlinx.android.synthetic.main.fragment_add_hosts.*
import kotlinx.android.synthetic.main.fragment_add_services.*
import kotlinx.android.synthetic.main.fragment_add_services_cours.*

import kotlinx.android.synthetic.main.fragment_add_services_logement.*

import kotlinx.android.synthetic.main.fragment_add_services_assistance.*


class AddServicesFragment: BaseFragment()  {
    var servicePosition = 1
    var assistancePriority = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_services, container, false)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        services_spinner.setSelection(1)
        services_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position == 0){
                    servicePosition = 0
                    services_cours_include.visibility = View.VISIBLE
                    services_assistance_include.visibility = View.GONE
                    services_logement_include.visibility = View.GONE
                }
                if(position == 1){
                    servicePosition = 1
                    services_cours_include.visibility = View.GONE
                    services_assistance_include.visibility = View.GONE
                    services_logement_include.visibility = View.VISIBLE
                }
                if(position == 2){
                    servicePosition = 2
                    services_cours_include.visibility = View.GONE
                    services_assistance_include.visibility = View.VISIBLE
                    services_logement_include.visibility = View.GONE

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                services_spinner.setSelection(1)
            }

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
        suivantButton.setOnClickListener{
            if(servicePosition==0){
                dbContext.insertCours(retrieveCours())
                clearTextEdit()
                Toast.makeText(context,"cours ajouté!!!",Toast.LENGTH_SHORT).show()
            }
            if(servicePosition==1){
                dbContext.insertLogement(retrieveLogement())
                clearTextEdit()
                Toast.makeText(context,"logement ajouté!!!",Toast.LENGTH_SHORT).show()
            }
            if(servicePosition==2){
                dbContext.insertAssistance(retrieveAssistance())
                clearTextEdit()
                Toast.makeText(context,"assistance ajouté!!!",Toast.LENGTH_SHORT).show()
            }
        }


    }
    private fun showSnackBar(){
        var services_view = R.string.host_added
        if(servicePosition == 0){
            services_view = R.string.cours_added
        }
        if(servicePosition == 1){
            services_view = R.string.host_added
        }
        if(servicePosition == 2){
            services_view = R.string.assistance_added
        }
        Snackbar.make(contextView,  services_view, Snackbar.LENGTH_SHORT)
            .setAction("Close") {
                Snackbar.make(contextView, "", Snackbar.LENGTH_SHORT)
                    .dismiss()
            }
            .show()
    }
    private fun clearTextEdit(){
        services_titre.text?.clear()
        services_phone.text?.clear()
        services_address.text?.clear()
        services_description.text?.clear()
        if(servicePosition == 0){
            cours_sessionduree.text?.clear()
            checkbox1_cours.isChecked = false
            checkbox2_cours.isChecked = false
            checkbox3_cours.isChecked = false
        }
        if(servicePosition == 1){
            logement_nombre_persone.text?.clear()
            logement_nombre_piece.text?.clear()
            checkbox1_logement.isChecked = false
            checkbox2_logement.isChecked = false
            checkbox3_logement.isChecked = false
        }
        if(servicePosition == 2){
            checkbox1_assistance.isChecked = false
            checkbox2_assistance.isChecked = false
            checkbox3_assistance.isChecked = false
        }
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
        var ret_assistance = Assistance(services_titre.text.toString(),services_description.text.toString(),services_address.text.toString(),
        services_phone.text.toString(),list,assistancePriority)
        return ret_assistance
    }
    private fun retrieveCours(): Lesson{
        var mlist = mutableListOf<String>()
        if(checkbox1_cours.isChecked){
            mlist.add(checkbox1_cours.text.toString())
        }
        if(checkbox2_cours.isChecked){
            mlist.add(checkbox2_cours.text.toString())
        }
        if(checkbox3_cours.isChecked){
            mlist.add(checkbox3_cours.text.toString())
        }
        var list = mlist.toList()
        var ret_cours = Lesson(services_titre.text.toString(),services_description.text.toString(),services_address.text.toString(),
            services_phone.text.toString(),list,cours_sessionduree.text.toString())
        return ret_cours
    }
    private fun retrieveLogement(): Host{
        var mlist = mutableListOf<String>()
        if(checkbox1_logement.isChecked){
            mlist.add(checkbox1_logement.text.toString())
        }
        if(checkbox2_logement.isChecked){
            mlist.add(checkbox2_logement.text.toString())
        }
        if(checkbox3_logement.isChecked){
            mlist.add(checkbox3_logement.text.toString())
        }
        var list = mlist.toList()
        var ret_logement = Host(services_titre.text.toString(),services_description.text.toString(),services_address.text.toString(),
            services_phone.text.toString(),list,logement_nombre_persone.text.toString(),logement_nombre_piece.text.toString())
        return ret_logement
    }
    private fun testaffiche() {

        Toast.makeText(context, services_titre.text.toString(), Toast.LENGTH_SHORT).show()
    }
}