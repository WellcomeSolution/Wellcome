package com.example.wellcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list_services.*

class SearchFragment : BaseFragment() {
    var pos = 0
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<CoursAdapter.ViewHolder>? = null
    private var adapter_host: RecyclerView.Adapter<HostAdapter.ViewHolder>? = null
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
        if(pos ==0 ){
            recycler_view.apply{
                layoutManager = LinearLayoutManager(activity)
                adapter= CoursAdapter(dbContext.getCours())
            }
        }
        if(pos ==1 ){
            recycler_view.apply{
                layoutManager = LinearLayoutManager(activity)
                adapter_host= HostAdapter(dbContext.getHosts())
            }
        }
        if(pos ==0 ){
            recycler_view.apply{
                layoutManager = LinearLayoutManager(activity)
                adapter= CoursAdapter(dbContext.getCours())
            }
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
                }
                if(position == 1){
                    pos = 1
                }
                if(position == 2) {
                    pos = 2
                }
                }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                search_spinner.setSelection(1)
            }
            }



        }

    }
