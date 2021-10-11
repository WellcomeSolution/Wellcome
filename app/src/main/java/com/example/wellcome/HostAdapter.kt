package com.example.wellcome
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wellcome.R
import com.example.wellcome.models.Host


class HostAdapter(private val dataSet: List<Host>):
    RecyclerView.Adapter<HostAdapter.ViewHolder>()
{
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.title_host)
        val address: TextView = itemView.findViewById(R.id.address_host)
        val phone: TextView = itemView.findViewById(R.id.phone_host)
        val tags: TextView = itemView.findViewById(R.id.tags_host)
        val nomberOfPeople: TextView = itemView.findViewById(R.id.nomber_people_host)
        val nomberOfPiece: TextView = itemView.findViewById(R.id.nomber_piece_host)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.host_card_view, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataSet[position].title
        viewHolder.phone.text = dataSet[position].phone
        viewHolder.address.text = dataSet[position].address
        viewHolder.tags.text = dataSet[position].tags.toString()
        viewHolder.nomberOfPeople.text = dataSet[position].nombrePersonne
        viewHolder.nomberOfPiece.text = dataSet[position].nombrePiece
    }

    override fun getItemCount() = dataSet.size

}