package com.example.wellcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wellcome.utils.db.Lesson

class FavoriteAssistanceAdapter(private val dataSet:List<Lesson>):RecyclerView.Adapter<FavoriteAssistanceAdapter.viewHolder>() {
    override fun onBindViewHolder(viewHolder: FavoriteAssistanceAdapter.viewHolder, position: Int) {
        viewHolder.title.text = dataSet[position].title
    }
    inner class viewHolder(view: View):RecyclerView.ViewHolder(view){
        val title :TextView = itemView.findViewById(R.id.title_lesson)
    }
    override fun onCreateViewHolder(parent: ViewGroup,viewType:Int):FavoriteAssistanceAdapter.viewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_assistance_card_view,parent,false)
        return viewHolder(view)

    }



    override fun getItemCount(): Int {
        return dataSet.size
    }

}