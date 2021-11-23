package com.example.wellcome
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragement_add_trip.view.*
import kotlinx.android.synthetic.main.trip_card_view.view.*


class TripAdapter(private val dataSet: List<com.example.wellcome.utils.db.Trip>):
    RecyclerView.Adapter<TripAdapter.ViewHolder>()
{
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val startD: TextView = itemView.stratdate_text
        val endD: TextView = itemView.enddate_trip
        val phone: TextView = itemView.phone_trip
        val nomberOfPeople: TextView = itemView.travelers_trip
        val country: TextView = itemView.country_trip
        val city: TextView = itemView.city_trip
        val postalCode: TextView = itemView.postalcode_trip
        val call_button: Button = itemView.call_button_trip
        val consultButton: Button = itemView.consulter_button_trip
        val email: TextView = itemView.Email_trip
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.trip_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.phone.text = dataSet[position].phone
        viewHolder.startD.text = dataSet[position].slotDate.startDate.toString()
        viewHolder.endD.text = dataSet[position].slotDate.endDate.toString()
        viewHolder.nomberOfPeople.text =dataSet[position].travelers.toString()
        viewHolder.country.text = dataSet[position].tripCity.country
        viewHolder.city.text = dataSet[position].tripCity.city
        viewHolder.postalCode.text = dataSet[position].tripCity.postalCode
        viewHolder.email.text = dataSet[position].adress
        //viewHolder.nomberOfPeople.text = dataSet[position].per
        //viewHolder.nomberOfPiece.text = dataSet[position].nombrePiece
        val tele = viewHolder.phone.text
        viewHolder.call_button.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$tele")
            context.startActivity(intent)
        }

    }

    override fun getItemCount() = dataSet.size
}