package com.example.wellcome

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.wellcome.utils.db.Address
import com.example.wellcome.utils.db.AppDatabase
import com.example.wellcome.utils.db.Assistance
import com.example.wellcome.utils.searchAddress
import java.lang.StringBuilder


class ConsultAssistanceAdapter(val context: Context, private val dataSet: Assistance):
    RecyclerView.Adapter<ConsultAssistanceAdapter.ViewHolder>() {

    lateinit var db: AppDatabase

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.title_assistance_con)
        val phone: TextView = itemView.findViewById(R.id.phone_con)
        val callButton: Button = itemView.findViewById(R.id.call_button_assistance_con)
        val country: TextView = itemView.findViewById(R.id.country_con)
        val city: TextView = itemView.findViewById(R.id.city_con)
        val postal_code: TextView = itemView.findViewById(R.id.postal_code_con)
        val thoroughfare: TextView = itemView.findViewById(R.id.thoroughfare_con)
        val description: EditText = itemView.findViewById(R.id.assistance_description_con)
        val priority: EditText = itemView.findViewById(R.id.assistance_priority_con)
        val checkbox1: CheckBox = itemView.findViewById(R.id.checkbox1_assistance_con)
        val checkbox2: CheckBox = itemView.findViewById(R.id.checkbox2_assistance_con)
        val checkbox3: CheckBox = itemView.findViewById(R.id.checkbox3_assistance_con)
        val reserve: Button = itemView.findViewById(R.id.reserve)
        val addressButton: Button = itemView.findViewById(R.id.search_address)
        val saveButton:Button = itemView.findViewById(R.id.add_favorites_assitance_con)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultAssistanceAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_consult_assistance_page, parent, false)

        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "wellcome"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1;
    }

    override fun onBindViewHolder(v: ViewHolder, position: Int) {
        v.title.text = dataSet.title

        v.country.text = dataSet.address.country?.addressLine
        v.city.text = dataSet.address.country?.administrativeArea?.locality?.addressLine
        v.postal_code.text = dataSet.address.country?.administrativeArea?.locality?.postalCode?.addressLine
        v.thoroughfare.text = dataSet.address.country?.administrativeArea?.locality?.thoroughfare?.addressLine

        v.phone.text = dataSet.phone
        v.description.setText(dataSet.description)
        v.priority.setText(dataSet.priority.toString())
        if(dataSet.tags.toString().contains(v.checkbox1.text)){
            v.checkbox1.isChecked
        }
        if(dataSet.tags.toString().contains(v.checkbox2.text)){
            v.checkbox2.isChecked
        }
        if(dataSet.tags.toString().contains(v.checkbox3.text)){
            v.checkbox3.isChecked
        }
        val tele = v.phone.text
        v.callButton.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$tele")
            context.startActivity(intent)
        }

        v.reserve.isEnabled = dataSet.isAvailable

        v.reserve.setOnClickListener {
            db.lessonDao().update(!v.reserve.isEnabled, dataSet.id)
            v.reserve.isEnabled = false
        }

        v.addressButton.setOnClickListener {
            context.searchAddress(getAddressRepresentation(dataSet.address))
        }

        v.saveButton.setOnClickListener{
            db.assistanceDao().update(true, dataSet.id)
        }
    }

    private fun getAddressRepresentation(address: Address): String{
        val sb = StringBuilder()
        sb.append(address.country?.addressLine + "\n")
        sb.append(address.country?.administrativeArea?.locality?.addressLine + "\n")
        sb.append(address.country?.administrativeArea?.locality?.thoroughfare?.addressLine)
        return sb.toString()
    }
}