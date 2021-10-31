package com.example.wellcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search_assistance.*


import android.app.AlertDialog
import java.util.*
import kotlin.collections.ArrayList
class SearchAssistanceFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search_assistance, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recycler_view_assistance.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=AssistanceAdapter(db.assistanceDao().getAll())
        }
        tags_assistance.setOnClickListener{
            withMultiChoiceList(it)
        }
        search_bar_assistance.setOnClickListener{
            val titleAssistance = services_titre.text.toString()
            recycler_view_assistance.adapter = context?.let { it1
                -> AssistanceAdapter(db.assistanceDao().findAssistanceByTitle(titleAssistance))}
        }
    }

    fun withMultiChoiceList(view: View){
        val items = arrayOf("Maintenance","Décoration","Livaison")
        val selectedList = ArrayList<Int>()
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Filters")
        builder.setMultiChoiceItems(items, null)
        {
                dialog, which,isChecked ->
            if(isChecked){
                selectedList.add(which)
            }
            else if(selectedList.contains(which)){
                selectedList.remove(Integer.valueOf(which))
            }
        }
        builder.setPositiveButton("Selected"){
                dialogInterface, i ->
            val selectedString = ArrayList<String>()
            var mlist = mutableListOf<String>()//list of tags
            for(j in selectedList.indices){
                selectedString.add(items[selectedList[j]])
                mlist.add(items[selectedList[j]])
            }
            Toast.makeText(context,"Items are :"+ Arrays.toString(selectedString.toTypedArray()),Toast.LENGTH_SHORT).show()
            var listTag = mlist.toList()
            recycler_view_assistance.adapter = context?.let { AssistanceAdapter( db.assistanceDao().findAssistanceByTags(listTag)) }
        }
        builder.show()
    }
}
