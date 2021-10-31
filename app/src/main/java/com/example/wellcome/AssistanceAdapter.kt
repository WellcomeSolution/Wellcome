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

class AssistanceAdapter(private val dataSet: List<Assistance>):
    RecyclerView.Adapter<AssistanceAdapter.ViewHolder>()
{
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.title_assistance)
        val address: TextView = itemView.findViewById(R.id.address_assistance)
        val phone: TextView = itemView.findViewById(R.id.phone_assistance)
        val call_button: Button = itemView.findViewById(R.id.call_button_assistance)
        val assistance_button: Button = itemView.findViewById(R.id.consulter_button_assistance)
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
        viewHolder.address.text = dataSet[position].address.toString()
        //viewHolder.tags.text = dataSet[position].tags.toString()
        //viewHolder.priority.text = dataSet[position].priority
        val tele = viewHolder.phone.text

        viewHolder.call_button.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$tele")
            context.startActivity(intent)
        }

        viewHolder.assistance_button.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("id",dataSet[position].id)
            val intent = Intent(context, ActivityConsultAssistance::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size
}