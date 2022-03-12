package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.services.HostPresenter
import com.example.services.IncomingTripDto
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.databinding.FragmentIncomingReservationsTravelersBinding
import kotlinx.android.synthetic.main.fragment_hosts.*
import kotlinx.android.synthetic.main.fragment_hosts.recyclerView
import kotlinx.android.synthetic.main.fragment_incoming_reservations_travelers.*

class IncomingReservationsTravelersFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private lateinit var incomingReservationsTravelerAdapter:IncomingReservationsTravelerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getIncomingTrip(userViewModel.email.value!!)

        userViewModel.incomingTrips.observe(viewLifecycleOwner, { trips : ArrayList<IncomingTripDto> ->
            incomingReservationsTravelerAdapter = IncomingReservationsTravelerAdapter(
                userViewModel.incomingTrips.value!!,
            )

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = incomingReservationsTravelerAdapter
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentIncomingReservationsTravelersBinding
            .inflate(inflater, container, false)
        binding.viewModel = userViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}