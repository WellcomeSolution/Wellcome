package com.example.wellcome

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wellcome.utils.db.Assistance
import com.example.wellcome.utils.db.Lesson

class FavoritesAdapter(private val dataset:List<Assistance>):RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataset[position].title

    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title:TextView=itemView.findViewById(R.id.title_lesson)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_lesson_card_view,parent,false)
        return ViewHolder(view)
    }



    override fun getItemCount(): Int {
        return dataset.size
    }

}