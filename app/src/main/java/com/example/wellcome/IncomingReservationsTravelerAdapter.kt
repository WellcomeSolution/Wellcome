package com.example.wellcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.services.FavoriteRequest
import com.example.services.HostPresenter
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.repository.Executor
import com.example.wellcome.repository.Result
import com.example.wellcome.repository.TripRepository
import com.example.wellcome.repository.TripResponseParser
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import android.app.Activity
import android.os.Handler
import android.os.Looper
import com.example.services.IncomingTripDto
import com.example.wellcome.data.FavoritesHostViewModel
import kotlinx.android.synthetic.main.fragment_host_details.*


class IncomingReservationsTravelerAdapter(private val dataSet: ArrayList<IncomingTripDto>) :
    RecyclerView.Adapter<IncomingReservationsTravelerAdapter.ViewHolder>() {
    private val hostPicture = "http://10.0.2.2:5229"
    var onItemClick: ((View, HostPresenter) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val address: TextView = view.findViewById(R.id.address)
        val img:ImageView = view.findViewById(R.id.host_picture)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.traveler_incoming_reservation_single_line, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataSet[position].hostTitle
        val address = "${dataSet[position].address?.country}, " +
                "${dataSet[position].address?.country}, " + "${dataSet[position].address?.postalCode}"
        viewHolder.address.text = address
        val pictureUrl = "${hostPicture}${dataSet[position].hostPhotoUrl}"
        Picasso.get().load(pictureUrl).into(viewHolder.img)
    }

    override fun getItemCount() = dataSet.size
}