package com.example.wellcome

import android.content.Context
import android.content.Intent
import android.content.RestrictionEntry
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.wellcome.models.Equipments
import com.example.wellcome.utils.db.*
import com.example.wellcome.utils.searchAddress
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.activity_consult_assistance_page.view.*
import kotlinx.android.synthetic.main.activity_consult_trip_page.view.*
import kotlinx.android.synthetic.main.cours_card_view.view.*
import kotlinx.android.synthetic.main.fragement_add_trip.view.*
import java.lang.StringBuilder

class ConsultTripAdapter (val context: Context, private val dataSet: Trip):
    RecyclerView.Adapter<ConsultTripAdapter.ViewHolder>(){
    lateinit var db: AppDatabase

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = itemView.image_trip_consult
        val email: TextView = itemView.email_trip_con
        val country: TextView = itemView.country_trip_con
        val city: TextView = itemView.city_trip_con
        val postal_code: TextView = itemView.postal_code_trip_con
        val addressLine: TextView = itemView.address_trip_con
        val chipGroup: ChipGroup = itemView.chipGroup_tripGroup
        val phone: TextView = itemView.phone_trip_con
        val Nbeds: TextView = itemView.number_beds
        val Nbathrooms : TextView = itemView.number_bathrooms
        val Nrooms : TextView = itemView.number_rooms
        val callButton: Button = itemView.call_button_trip_con
        val addFavoriteButton: Button = itemView.add_favorites_trip_con
        val checkbox1: CheckBox = itemView.checkbox1_trip_con
        val checkbox2: CheckBox = itemView.checkbox2_trip_con
        val checkbox3: CheckBox = itemView.checkbox3_trip_con
        val checkbox4: CheckBox = itemView.checkbox1_equipment_con
        val checkbox5: CheckBox = itemView.checkbox2_equipment_con
        val checkbox6: CheckBox = itemView.checkbox3_equipment_con
        val reserve: Button = itemView.reserve_trip
        val addressButton: Button = itemView.search_trip_address

        val likeButton: Button = itemView.findViewById(R.id.like_trip)
        var likeCount: TextView =itemView.findViewById(R.id.like_count_t)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultTripAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_consult_trip_page, parent, false)

        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "wellcome"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        return ViewHolder(view)
    }
    private fun getAddressRepresentation(address: TripCity): String{
        val sb = StringBuilder()
        sb.append(address.country + "\n")
        sb.append(address.city + "\n")
        sb.append(address.address)
        return sb.toString()
    }
     override fun onBindViewHolder(v: ConsultTripAdapter.ViewHolder, position: Int) {
         v.imageView.setImageURI(Uri.parse(dataSet.image))
         v.email.text = dataSet.adress
        v.country.text = dataSet.tripCity.country
         v.phone.text = dataSet.phone
         v.addressLine.text= dataSet.tripCity.address
        v.city.text = dataSet.tripCity.city
        v.postal_code.text = dataSet.tripCity.postalCode
        v.Nbeds.text =dataSet.hostConfiguration.beds.toString()
         v.Nbathrooms.text = dataSet.hostConfiguration.bathrooms.toString()
         v.Nrooms.text = dataSet.hostConfiguration.rooms.toString()
         var count  = dataSet.like_trip

         if(dataSet.hasBabies){
             v.checkbox1.isChecked
         }
         if(dataSet.hasDogs){
             v.checkbox2.isChecked
         }
         if(dataSet.hasChilds){
             v.checkbox3.isChecked
         }
         if(dataSet.hostConfiguration.equipments.contains(Equipments.Wifi)) v.checkbox4.isChecked
         if(dataSet.hostConfiguration.equipments.contains(Equipments.HotWater)) v.checkbox5.isChecked
         if(dataSet.hostConfiguration.equipments.contains(Equipments.WashingMachine)) v.checkbox6.isChecked
         val tele = dataSet.phone
         v.callButton.setOnClickListener {
             var intent = Intent()
             intent.action = Intent.ACTION_DIAL
             intent.data = Uri.parse("tel:$tele")
             context.startActivity(intent)
         }
         v.reserve.setOnClickListener{
             val intent = Intent(context, RequestActivity::class.java)
             context.startActivity(intent)
         }
         v.addressButton.setOnClickListener{
             context.searchAddress(getAddressRepresentation(dataSet.tripCity))
         }
         v.addFavoriteButton.setOnClickListener{
             db.tripDao().update(true, dataSet.id)
         }
         v.likeButton.setOnClickListener{
             db.tripDao().updateLike(dataSet.id)
             count +=1
             v.likeCount.text = count.toString()
         }
    }

    override fun getItemCount(): Int {
        return 1;
    }


}