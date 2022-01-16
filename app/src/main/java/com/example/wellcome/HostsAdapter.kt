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


class HostsAdapter(private val dataSet: ArrayList<HostPresenter>,
                   val userViewModel: UserViewModel) :
    RecyclerView.Adapter<HostsAdapter.ViewHolder>() {
    private val hostPicture = "http://10.0.2.2:5229"
    private val tripRepository = TripRepository(Executor(), TripResponseParser())
    val mainLooper = Looper.getMainLooper()
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
        viewHolder.favoriteButton.setOnClickListener{
            if(!dataSet[position].isFavorite)
                addFromFavorite(viewHolder, position)
            else
                removeFromFavorite(viewHolder, position)
        }
    }

    private fun addFromFavorite(viewHolder: ViewHolder, position: Int){
        viewHolder.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
        tripRepository.setFavoriteHost(FavoriteRequest(userViewModel.email.value!!, dataSet[position].uuid)) { result ->
            when(result){
                is Result.SuccessNoContent -> {
                    Handler(mainLooper).post {
                        dataSet[position].isFavorite = true
                        viewHolder.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
                    }
                }
            }
        }
    }

    private fun removeFromFavorite(viewHolder: ViewHolder, position: Int){
        viewHolder.favoriteButton.setImageResource(R.drawable.baseline_favorite_border_24)
        tripRepository.removeFavoriteHost(FavoriteRequest(userViewModel.email.value!!, dataSet[position].uuid)) { result ->
            when(result){
                is Result.SuccessNoContent -> {
                    Handler(mainLooper).post {
                        dataSet[position].isFavorite = false
                        viewHolder.favoriteButton.setImageResource(R.drawable.baseline_favorite_border_24)
                    }
                }
            }
        }
    }

    private fun getFavoriteUri(presenter: HostPresenter) : Int
        = if(presenter.isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24

    override fun getItemCount() = dataSet.size
}