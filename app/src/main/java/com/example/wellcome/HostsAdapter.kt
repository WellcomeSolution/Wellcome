package com.example.wellcome

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.services.Host
import com.example.services.HostPresenter
import com.example.services.Trip
import com.example.wellcome.repository.City
import com.example.wellcome.repository.Result
import com.example.wellcome.repository.TripRepository
import com.example.wellcome.repository.TripResponseParser
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import java.util.concurrent.Executor

class HostsAdapter(private val dataSet: ArrayList<HostPresenter>) :
    RecyclerView.Adapter<HostsAdapter.ViewHolder>() {
    private val hostPicture = "http://10.0.2.2:5229/api/hosts/picture"
    var onItemClick: ((View) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactTextView: TextView = view.findViewById(R.id.contact)
        val localisationTextView:TextView = view.findViewById(R.id.localisation)
        val img:RoundedImageView = view.findViewById(R.id.img)
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
        Picasso.get().load("${hostPicture}/${dataSet[position].pictureId}").into(viewHolder.img)
        viewHolder.itemView.transitionName = "transition${position}"
        viewHolder.itemView.setOnClickListener {
            onItemClick?.invoke(viewHolder.itemView)
        }
    }

    override fun getItemCount() = dataSet.size
}