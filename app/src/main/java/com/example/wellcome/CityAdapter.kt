package com.example.wellcome

import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.example.wellcome.utils.City

class CityAdapter(countries : Collection<City>) :
    RecyclerView.Adapter<ViewHolder>(), Filterable {
    var cities: ArrayList<String> = ArrayList()
    var filteredCities: ArrayList<String> = ArrayList()

    init {
        cities = ArrayList(countries.map { it.name }.distinct())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SingleLineItemViewHolder.create(parent);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return bind((holder as SingleLineItemViewHolder?)!!, position)
    }

    private fun bind(vh: SingleLineItemViewHolder, position: Int) {
        vh.text.text = filteredCities[position]
        vh.icon.setImageResource(R.drawable.outline_share_location_24)
    }

    override fun getItemCount() = filteredCities.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                filteredCities = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<String>
                notifyDataSetChanged()
            }

            override fun performFiltering(constraint: CharSequence): FilterResults? {
                val results = FilterResults()

                filteredCities = ArrayList(cities.filter {
                    it.startsWith(constraint, ignoreCase = true) })

                results.count = filteredCities.size
                results.values = filteredCities
                
                return results
            }
        }
    }

}