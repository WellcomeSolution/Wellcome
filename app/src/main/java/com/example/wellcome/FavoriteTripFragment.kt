package com.example.wellcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorite_lesson.*
import kotlinx.android.synthetic.main.fragment_favorite_lesson.recycler_view_favorites
import kotlinx.android.synthetic.main.fragment_favorite_trip.*


class FavoriteTripFragment : BaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view_favorites.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = FavoritesTripAdapter(context, db.tripDao().findTripFavorites())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_trip, container, false)
    }



}