package com.example.wellcome
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wellcome.R
import com.example.wellcome.models.Assistance
import com.example.wellcome.utils.call

class AssistanceAdapter(private val dataSet: List<Assistance>):
    RecyclerView.Adapter<AssistanceAdapter.ViewHolder>()
{
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.title_assistance)
        val address: TextView = itemView.findViewById(R.id.address_assistance)
        val phone: TextView = itemView.findViewById(R.id.phone_assistance)
        val tags: TextView = itemView.findViewById(R.id.tags_assistance)
        val priority: TextView = itemView.findViewById(R.id.priority_assistance)

        init {
            val callButton : TextView =  itemView.findViewById(R.id.call_button_assistance)
            callButton.setOnClickListener{
                context.call(Integer.parseInt(phone.text.toString()))
            }
        }
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
        viewHolder.address.text = dataSet[position].address
        viewHolder.tags.text = dataSet[position].tags.toString()
        viewHolder.priority.text = dataSet[position].priority
    }

    override fun getItemCount() = dataSet.size

}