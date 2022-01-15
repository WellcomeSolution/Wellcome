package com.example.wellcome

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.example.wellcome.repository.Address
import com.example.wellcome.repository.City

class CityAdapter(private val data : ArrayList<City>) :
    RecyclerView.Adapter<ViewHolder>() {
    val cities = data
    var onItemClick: ((City) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SingleLineItemViewHolder.create(parent);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder as SingleLineItemViewHolder
        view.itemView.setOnClickListener {
            onItemClick?.invoke(cities[position])
        }
        bind(view, position)
    }

    private fun bind(vh: SingleLineItemViewHolder, position: Int) {
        vh.text.text = cities[position].address?.town
        vh.icon.setImageResource(R.drawable.baseline_share_location_24)
    }

    override fun getItemCount() = cities.size
}