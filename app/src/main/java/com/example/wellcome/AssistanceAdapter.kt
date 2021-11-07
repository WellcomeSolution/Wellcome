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
import com.example.wellcome.utils.db.Assistance
import kotlinx.android.synthetic.main.assistance_card_view.view.*

class AssistanceAdapter(private val dataSet: List<Assistance>):
    RecyclerView.Adapter<AssistanceAdapter.ViewHolder>()
{
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.title_assistance
        val phone: TextView = itemView.phone_assistance
        val callButton: Button = itemView.call_button_assistance
        val assistanceButton: Button = itemView.consulter_button_assistance
        val country: TextView = itemView.country_assistance
        val department: TextView = itemView.department_assistance
        val city: TextView = itemView.city_assistance
        val postalCode: TextView =itemView.postalcode_assistance

        val address: TextView = itemView.findViewById(R.id.address_assistance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssistanceAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.assistance_card_view, parent, false)

        context = parent.context

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataSet[position].title
        viewHolder.phone.text = dataSet[position].phone
        viewHolder.country.text = dataSet[position].address.country?.addressLine
        viewHolder.department.text = dataSet[position].address.country?.administrativeArea?.addressLine
        viewHolder.city.text = dataSet[position].address.country?.administrativeArea?.locality?.addressLine
        viewHolder.postalCode.text = dataSet[position].address.country?.administrativeArea?.locality?.postalCode?.addressLine
        viewHolder.address.text = dataSet[position].address.country?.administrativeArea?.locality?.thoroughfare?.addressLine

        val tele = viewHolder.phone.text

        viewHolder.callButton.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$tele")
            context.startActivity(intent)
        }

        viewHolder.assistanceButton.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("id",dataSet[position].id)
            val intent = Intent(context, ActivityConsultAssistance::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size
}