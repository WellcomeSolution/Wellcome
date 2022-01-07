package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.FragmentHostsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.fragment_hosts.*
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.wellcome.data.HostViewModel
import kotlinx.android.synthetic.main.fragment_hosts.top_app_bar
import kotlinx.android.synthetic.main.fragment_trip_configuration_modify.*
import kotlinx.android.synthetic.main.top_app_bar.*


class HostsFragment : Fragment() {
    private val sharedTripViewModel: SharedTripViewModel by activityViewModels()
    private val hostViewModel: HostViewModel by navGraphViewModels(R.id.hostFragment)
    private lateinit var hostsAdapter:HostsAdapter

    companion object{
        val modalBottomSheet : ModifyTripSearchBottomSheet = ModifyTripSearchBottomSheet()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        hostsAdapter = HostsAdapter(sharedTripViewModel.hostPresenters.value!!)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = hostsAdapter
        }

        hostsAdapter.onItemClick = { view, host ->
            hostViewModel.title.value = host.title
            hostViewModel.loadHostDetails(host.id)
            val extras = FragmentNavigatorExtras(view to "transition")
            val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            val directions = HostsFragmentDirections.navigateToHostDetails()
            nav.navigate(directions, extras)
        }

        trip_descriptions.setOnClickListener{
            modalBottomSheet.show(childFragmentManager, ModifyTripSearchBottomSheet.TAG)
        }

        top_app_bar.setOnClickListener{
            val nav = findNavController()
            nav.popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        val binding = FragmentHostsBinding
            .inflate(inflater, container, false)
        binding.viewModel = sharedTripViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}