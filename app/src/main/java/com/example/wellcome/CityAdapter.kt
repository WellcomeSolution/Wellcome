package com.example.wellcome

import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.example.wellcome.utils.City

class CityAdapter(private val countries : ArrayList<com.example.wellcome.repository.City>) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SingleLineItemViewHolder.create(parent);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return bind((holder as SingleLineItemViewHolder?)!!, position)
    }

    private fun bind(vh: SingleLineItemViewHolder, position: Int) {
        vh.text.text = countries[position].name
        vh.icon.setImageResource(R.drawable.baseline_share_location_24)
    }

    override fun getItemCount() = countries.size
}