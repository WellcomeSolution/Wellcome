package com.example.wellcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import kotlinx.android.synthetic.main.fragment_add_hosts.*
import kotlinx.android.synthetic.main.fragment_add_services.*

class AddServicesFragment: BaseFragment()  {

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
                    services_cours.visibility = View.VISIBLE
                    services_assistance.visibility = View.GONE
                    services_logement.visibility = View.GONE
                }
                if(position == 1){
                    services_cours.visibility = View.GONE
                    services_assistance.visibility = View.GONE
                    services_logement.visibility = View.VISIBLE
                }
                if(position == 2){
                    services_cours.visibility = View.GONE
                    services_assistance.visibility = View.VISIBLE
                    services_logement.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                services_spinner.setSelection(1)
            }

        }
        suivantButton.setOnClickListener{

        }


    }
}