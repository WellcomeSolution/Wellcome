package com.example.wellcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.services.Host

class HostsAdapter(private val dataSet: ArrayList<Host>) :
    RecyclerView.Adapter<HostsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactTextView: TextView = view.findViewById(R.id.contact)
        val localisationTextView:TextView = view.findViewById(R.id.localisation)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.host_card_view_new, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val contact = "${dataSet[position].contact.firstName} ${dataSet[position].contact.lastName}"
        viewHolder.contactTextView.text = contact
        val localisation = "${dataSet[position].address.country}, ${dataSet[position].address.city}"
        viewHolder.localisationTextView.text = localisation
    }

    override fun getItemCount() = dataSet.size
}