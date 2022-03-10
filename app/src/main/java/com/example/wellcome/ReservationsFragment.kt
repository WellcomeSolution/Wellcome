package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.services.HostPresenter
import com.example.services.HostReservationPresenterDto
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.databinding.FragmentFavoritesHostBinding
import com.example.wellcome.databinding.FragmentReservationsBinding
import kotlinx.android.synthetic.main.fragment_hosts.*
import kotlinx.android.synthetic.main.fragment_hosts.recyclerView
import kotlinx.android.synthetic.main.fragment_reservations.*

class ReservationsFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var reservationsAdapter: ReservationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentReservationsBinding
            .inflate(inflater, container, false)
        binding.viewModel = userViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.loadReservations()

        userViewModel.reservations.observe(viewLifecycleOwner, { reservations : ArrayList<HostReservationPresenterDto> ->
            reservationsAdapter = ReservationsAdapter(userViewModel.reservations.value!!, userViewModel)

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = reservationsAdapter
            }
        })
    }
}