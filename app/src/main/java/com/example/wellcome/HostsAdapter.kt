package com.example.wellcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.services.HostPresenter
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class HostsAdapter(private val dataSet: ArrayList<HostPresenter>) :
    RecyclerView.Adapter<HostsAdapter.ViewHolder>() {
    private val hostPicture = "http://10.0.2.2:5229"
    var onItemClick: ((View, HostPresenter) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactTextView: TextView = view.findViewById(R.id.contact)
        val localisationTextView:TextView = view.findViewById(R.id.localisation)
        val img:RoundedImageView = view.findViewById(R.id.img)
        val favoriteButton:ImageView = view.findViewById(R.id.favorite_button)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.host_card_view_new, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val contact = "${dataSet[position].firstName} ${dataSet[position].lastName}"
        viewHolder.contactTextView.text = contact
        val localisation = "${dataSet[position].country}, ${dataSet[position].city}"
        viewHolder.localisationTextView.text = localisation
        val pictureUrl = "${hostPicture}${dataSet[position].pictureUrl}"
        Picasso.get().load(pictureUrl).into(viewHolder.img)
        viewHolder.itemView.transitionName = "transition${position}"
        viewHolder.favoriteButton.setImageResource(getFavoriteUri(dataSet[position]))
        viewHolder.itemView.setOnClickListener {
            onItemClick?.invoke(viewHolder.itemView, dataSet[position])
        }
    }

    private fun getFavoriteUri(presenter: HostPresenter) : Int
        = if(presenter.isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24

    override fun getItemCount() = dataSet.size
}