package com.example.wellcome
import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.cours_card_view.view.*
import kotlinx.android.synthetic.main.fragement_add_trip.view.*
import kotlinx.android.synthetic.main.trip_card_view.view.*
import android.app.Activity

import androidx.core.app.ActivityCompat
import java.lang.Exception
import android.content.pm.PackageManager

import android.os.Build
import androidx.room.Room
import com.example.wellcome.utils.db.AppDatabase


class FavoritesTripAdapter(val context: Context,private val dataSet: List<com.example.wellcome.utils.db.Trip>):
    RecyclerView.Adapter<FavoritesTripAdapter.ViewHolder>()
{
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView:ImageView = itemView.image_trip
        val email: TextView = itemView.Email_trip
        val address: TextView = itemView.address_trip
        val call_button: Button = itemView.call_button_trip
        val consultButton: Button = itemView.consulter_button_trip
        val addFavoriteButton: Button = itemView.add_favorites_trip
        val chipGroup : ChipGroup = itemView.chipGroup_trip
        val checkDistanceButton:Button = itemView.check_distances_trip
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesTripAdapter.ViewHolder {
        if(!checkPermissionForReadExtertalStorage())
            requestPermissionForReadExtertalStorage()

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.trip_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.imageView.setImageURI(Uri.parse(dataSet[position].image))
        viewHolder.address.text = dataSet[position].tripCity.address
        viewHolder.email.text = dataSet[position].adress
        val tele = dataSet[position].phone
        viewHolder.call_button.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$tele")
            context.startActivity(intent)
        }
        viewHolder.consultButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id",dataSet[position].id)
            bundle.putString("address",dataSet[position].tripCity?.address)
            val intent = Intent(context,ActivityConsultTrip::class.java)
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
                db.tripDao().update(false, dataSet[position].id)
                viewHolder.addFavoriteButton.text = "Save"
                isFavorite = false
            }
            else {
                db.tripDao().update(true, dataSet[position].id)
                viewHolder.addFavoriteButton.text = "Unsave"
                isFavorite = true
            }
        }
    }

    private val READ_STORAGE_PERMISSION_REQUEST_CODE = 41
    fun checkPermissionForReadExtertalStorage(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result = context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    @Throws(Exception::class)
    fun requestPermissionForReadExtertalStorage() {
        try {
            ActivityCompat.requestPermissions(
                (context as Activity), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_STORAGE_PERMISSION_REQUEST_CODE
            )
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun getItemCount() = dataSet.size
}