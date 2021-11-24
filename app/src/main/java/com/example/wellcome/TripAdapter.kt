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
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.cours_card_view.view.*
import kotlinx.android.synthetic.main.fragement_add_trip.view.*
import kotlinx.android.synthetic.main.trip_card_view.view.*


class TripAdapter(val context: Context,private val dataSet: List<com.example.wellcome.utils.db.Trip>):
    RecyclerView.Adapter<TripAdapter.ViewHolder>()
{

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val email: TextView = itemView.Email_trip
        val address: TextView = itemView.address_trip
        val call_button: Button = itemView.call_button_trip
        val consultButton: Button = itemView.consulter_button_trip
        val addFavoriteButton: Button = itemView.add_favorites_trip
        val chipGroup : ChipGroup = itemView.chipGroup_trip
        val checkDistanceButton:Button = itemView.check_distances_trip
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.trip_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
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

    }

    override fun getItemCount() = dataSet.size
}