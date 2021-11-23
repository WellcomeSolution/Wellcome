package com.example.wellcome

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wellcome.utils.db.Assistance

class FavoritesAssistanceAdapter(private val dataset:List<Assistance>):RecyclerView.Adapter<FavoritesAssistanceAdapter.ViewHolder>() {
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
            val intent = Intent(context, ActivityConsultAssistance::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title:TextView=itemView.findViewById(R.id.title_assistance_fav)
        val address:TextView=itemView.findViewById(R.id.address_fav)
        val consultButton:TextView=itemView.findViewById(R.id.consulter_button_assistance_fav)
        val callButton:TextView=itemView.findViewById(R.id.call_button_assistance_fav)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAssistanceAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_assistance_card_view,parent,false)

        context = parent.context

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}