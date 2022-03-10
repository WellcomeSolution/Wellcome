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
import com.example.services.HostReservationPresenterDto
import com.example.wellcome.data.FavoritesHostViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_host_details.*


class ReservationsAdapter(private val dataSet: ArrayList<HostReservationPresenterDto>, private val userViewModel: UserViewModel) :
    RecyclerView.Adapter<ReservationsAdapter.ViewHolder>() {
    private val hostPicture = "http://10.0.2.2:5229"

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstName: TextView = view.findViewById(R.id.first_name)
        val message:TextView = view.findViewById(R.id.message)
        val date: TextView = view.findViewById(R.id.date)
        val hostTitle:TextView = view.findViewById(R.id.hostTitle)
        val profilePhoto:ImageView = view.findViewById(R.id.profile_image)
        val acceptButton:MaterialButton = view.findViewById(R.id.accept)
        val declineButton:MaterialButton = view.findViewById(R.id.decline)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.request_list_item_single_line, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.firstName.text = dataSet[position].firstName
        viewHolder.message.text = dataSet[position].firstName
        val date = "${dataSet[position].startDate}${dataSet[position].endDate}"
        viewHolder.date.text = date
        viewHolder.hostTitle.text = dataSet[position].hostTitle
        val pictureUrl = "${hostPicture}${dataSet[position].applicantPhoto}"
        Picasso.get().load(pictureUrl).into(viewHolder.profilePhoto)

        viewHolder.acceptButton.setOnClickListener{
            userViewModel.acceptReservation(dataSet[position].uuid!!)
            dataSet.remove(dataSet[position])
            this.notifyDataSetChanged()
        }

        viewHolder.declineButton.setOnClickListener{
            userViewModel.declineReservation(dataSet[position].uuid!!)
            dataSet.remove(dataSet[position])
            this.notifyDataSetChanged()
        }
    }

    override fun getItemCount() = dataSet.size
}