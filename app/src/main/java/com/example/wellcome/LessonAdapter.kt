package com.example.wellcome
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.wellcome.utils.DistanceCalculator
import com.example.wellcome.utils.db.AppDatabase
import com.example.wellcome.utils.db.Lesson
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.assistance_card_view.view.*
import kotlinx.android.synthetic.main.cours_card_view.view.*
import kotlinx.android.synthetic.main.trip_card_view.view.*
import java.util.*

class LessonAdapter(val context: Context,private val dataSet: List<Lesson>):
    RecyclerView.Adapter<LessonAdapter.ViewHolder>()
{
    var latitude:Double = 48.9891
    var longitude:Double = 2.2585

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = itemView.image_lesson
        val title: TextView = itemView.title_lesson
        val address: TextView = itemView.address_lesson
        val callButton: Button = itemView.call_button_lesson
        val consultButton: Button = itemView.consulter_button_lesson
        val addFavoriteButton: Button = itemView.add_favorites_lesson
        val chipGroup : ChipGroup = itemView.chipGroup_lesson
        val checkDistanceButton:Button = itemView.check_distances_lesson

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cours_card_view, parent, false)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.imageView.setImageURI(Uri.parse(dataSet[position].image))
        dataSet[position].tags.forEach {
            val chip = Chip(context)
            chip.text = it
            viewHolder.chipGroup.addView(chip)
        }

        viewHolder.title.text = dataSet[position].title
        val city = dataSet[position].address.country?.administrativeArea?.locality?.addressLine
        val country = dataSet[position].address.country?.addressLine
        viewHolder.address.text = "$city " +
                "in $country"

        val phone  = dataSet[position].phone

        viewHolder.callButton.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$phone")
            context.startActivity(intent)
        }

        viewHolder.consultButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id",dataSet[position].id)
            bundle.putString("address",dataSet[position].address.country?.administrativeArea?.locality?.thoroughfare?.addressLine)
            val intent = Intent(context,ActivityConsultLesson::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "wellcome"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        var isFavorite = dataSet[position].isFavorite
        viewHolder.addFavoriteButton.setOnClickListener{
            if(isFavorite){
                db.lessonDao().update(false, dataSet[position].id)
                viewHolder.addFavoriteButton.text = "Save"
                isFavorite = false
            }
            else {
                db.lessonDao().update(true, dataSet[position].id)
                viewHolder.addFavoriteButton.text = "Unsave"
                isFavorite = true
            }
        }

        if(dataSet[position].isFavorite) {
            viewHolder.addFavoriteButton.text = "Unsave"
        }
        else {
            viewHolder.addFavoriteButton.text = "Save"
        }

        viewHolder.checkDistanceButton.setOnClickListener{
            //getLastKnownLocation()
            val geo = Geocoder(context, Locale.getDefault())
            val res = geo.getFromLocationName("Paris",1)
            val distance = DistanceCalculator.Companion.getDistance(latitude, longitude, 48.8924,2.2153)
            Toast.makeText(context, "The distance is ${distance.toInt()} km", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount() = dataSet.size
}