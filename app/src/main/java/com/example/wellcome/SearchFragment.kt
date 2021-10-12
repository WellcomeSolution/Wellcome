package com.example.wellcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_add_services_cours.*
import kotlinx.android.synthetic.main.fragment_list_services.*
import kotlinx.android.synthetic.main.fragment_list_services.checkbox1_cours_search
import kotlinx.android.synthetic.main.fragment_list_services.checkbox_cours
import kotlinx.android.synthetic.main.fragment_list_services.checkbox1_logement_search
import kotlinx.android.synthetic.main.fragment_list_services.checkbox_logement
import kotlinx.android.synthetic.main.fragment_list_services.checkbox1_assistance_search
import kotlinx.android.synthetic.main.fragment_list_services.checkbox_assistance

class SearchFragment : BaseFragment() {
    var pos = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_services, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        search_spinner.setSelection(1)
        recycler_view.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=CoursAdapter(dbContext.getCours())
        }
        recycler_view_host.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=HostAdapter(dbContext.getHosts())
        }
        recycler_view_assistance.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=AssistanceAdapter(dbContext.getAssistances())
        }
        search_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position == 0){
                    pos = 0
                    checkbox_cours.visibility = View.VISIBLE
                    checkbox_logement.visibility = View.GONE
                    checkbox_assistance.visibility = View.GONE
                    recycler_view.visibility = View.VISIBLE
                    recycler_view_host.visibility = View.GONE
                    recycler_view_assistance.visibility = View.GONE

                }
                if(position == 1){
                    pos = 1
                    checkbox_cours.visibility = View.GONE
                    checkbox_logement.visibility = View.VISIBLE
                    checkbox_assistance.visibility = View.GONE
                    recycler_view.visibility = View.GONE
                    recycler_view_host.visibility = View.VISIBLE
                    recycler_view_assistance.visibility = View.GONE
                }
                if(position == 2) {
                    pos = 2
                    checkbox_cours.visibility = View.GONE
                    checkbox_logement.visibility = View.GONE
                    checkbox_assistance.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                    recycler_view_host.visibility = View.GONE
                    recycler_view_assistance.visibility = View.VISIBLE
                }
                }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                search_spinner.setSelection(1)
            }
            }
        search_tags_button.setOnClickListener{
            var mlist = mutableListOf<String>()

            if(pos == 0){
                  if(checkbox1_cours_search.isChecked){
                      mlist.add(checkbox1_cours_search.text.toString())
                  }
                  if(checkbox2_cours_search.isChecked){
                    mlist.add(checkbox2_cours_search.text.toString())
                }
                  if(checkbox3_cours_search.isChecked){
                    mlist.add(checkbox3_cours_search.text.toString())
                }
                var listtags = mlist.toList()
                Toast.makeText(context,listtags.toString(),Toast.LENGTH_SHORT).show()
                recycler_view.visibility = View.GONE
                recycler_view_test.apply {
                    layoutManager=LinearLayoutManager(activity)
                    adapter=CoursAdapter(dbContext.searchCoursByTags(listtags))
                }
            }
            if(pos == 1){
                if(checkbox1_logement_search.isChecked){
                    mlist.add(checkbox1_logement_search.text.toString())
                }
                if(checkbox2_logement_search.isChecked){
                    mlist.add(checkbox2_logement_search.text.toString())
                }
                if(checkbox3_logement_search.isChecked){
                    mlist.add(checkbox3_logement_search.text.toString())
                }
                var listtags = mlist.toList()
                Toast.makeText(context,listtags.toString(),Toast.LENGTH_SHORT).show()
                recycler_view_host.visibility = View.GONE
                recycler_view_test.apply {
                    layoutManager=LinearLayoutManager(activity)
                    adapter=HostAdapter(dbContext.searchHostsByTags(listtags))
                }
            }
            if(pos == 2){
                if(checkbox1_assistance_search.isChecked){
                    mlist.add(checkbox1_assistance_search.text.toString())
                }
                if(checkbox2_assistance_search.isChecked){
                    mlist.add(checkbox2_assistance_search.text.toString())
                }
                if(checkbox3_assistance_search.isChecked){
                    mlist.add(checkbox3_assistance_search.text.toString())
                }
                var listtags = mlist.toList()
                Toast.makeText(context,listtags.toString(),Toast.LENGTH_SHORT).show()
                recycler_view_assistance.visibility = View.GONE
                recycler_view_test.apply {
                    layoutManager=LinearLayoutManager(activity)
                    adapter=AssistanceAdapter(dbContext.searchAssistancesByTags(listtags))
                }
            }


        }



        }

    }
