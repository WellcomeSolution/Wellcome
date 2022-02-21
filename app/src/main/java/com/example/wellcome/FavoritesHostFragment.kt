package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.services.HostPresenter
import com.example.wellcome.data.FavoritesHostViewModel
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.databinding.FragmentFavoritesHostBinding
import kotlinx.android.synthetic.main.fragment_hosts.*

class FavoritesHostFragment : Fragment() {
    private val favoritesHostViewModel: FavoritesHostViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var favoritesHostAdapter:FavoritesHostAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesHostViewModel.loadFavoritesHost(userViewModel.email.value!!)

        favoritesHostViewModel.favoriteHostPresenters.observe(viewLifecycleOwner, { hosts : ArrayList<HostPresenter> ->
            favoritesHostAdapter = FavoritesHostAdapter(
                favoritesHostViewModel.favoriteHostPresenters.value!!,
                favoritesHostViewModel
            )

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = favoritesHostAdapter
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavoritesHostBinding
            .inflate(inflater, container, false)
        binding.viewModel = favoritesHostViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}