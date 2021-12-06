package com.example.wellcome

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.wellcome.utils.db.AppDatabase
import com.example.wellcome.utils.db.Assistance
import com.example.wellcome.utils.db.Lesson
import kotlinx.android.synthetic.main.cours_card_view.view.*

class FavoritesLessonAdapter(private val dataset:List<Lesson>):RecyclerView.Adapter<FavoritesLessonAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataset[position].title
        val city = dataset[position].address.country?.administrativeArea?.locality?.addressLine
        val country = dataset[position].address.country?.addressLine
        viewHolder.address.text = "$city " +
                "in $country"

        val phone  = dataset[position].phone

        viewHolder.callButton.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$phone")
            context.startActivity(intent)
        }

        viewHolder.consultButton.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("id",dataset[position].id)
            val intent = Intent(context, ActivityConsultLesson::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "wellcome"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title:TextView=itemView.findViewById(R.id.title_lesson_fav)
        val address:TextView=itemView.findViewById(R.id.address_lesson_fav)
        val consultButton:TextView=itemView.findViewById(R.id.consulter_button_lesson_fav)
        val callButton:TextView=itemView.findViewById(R.id.call_button_lesson_fav)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesLessonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_lesson_card_view,parent,false)

        context = parent.context

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}