package com.example.wellcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search_lesson.*

import android.app.AlertDialog
import com.example.wellcome.utils.db.Lesson
import java.util.*
import kotlin.collections.ArrayList
class SearchLessonFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_lesson, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recycler_view.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=LessonAdapter(context,db.lessonDao().getAll())
     }
        tags_lesson.setOnClickListener{
            withMultiChoiceList(it)
        }
        search_bar_lesson.setOnClickListener{
            val titleLesson = lesson_titre.text.toString()
            recycler_view.adapter = this.context?.let { it1 -> LessonAdapter(it1,db.lessonDao().findLessonByTitle(titleLesson)) }

        }
        }
    fun withMultiChoiceList(view: View){
        val items = arrayOf("Informatique","Marking","Bac")
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
            var MLesson = mutableListOf<Lesson>()
            for(x in listTag){
                MLesson.addAll(db.lessonDao().findLessonByOneTag(x))
            }
            var listtest = MLesson.distinct().toList()
            recycler_view.adapter = context?.let { it->LessonAdapter(it,listtest) }
        }
        builder.show()
    }
    }
