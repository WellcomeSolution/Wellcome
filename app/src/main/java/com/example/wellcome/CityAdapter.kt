package com.example.wellcome

import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

class CityAdapter(private val data : ArrayList<com.example.wellcome.repository.Address>) :
    RecyclerView.Adapter<ViewHolder>() {
    val cities = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SingleLineItemViewHolder.create(parent);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return bind((holder as SingleLineItemViewHolder?)!!, position)
    }

    private fun bind(vh: SingleLineItemViewHolder, position: Int) {
        vh.text.text = cities[position].city
        vh.icon.setImageResource(R.drawable.baseline_share_location_24)
    }

    override fun getItemCount() = cities.size
}