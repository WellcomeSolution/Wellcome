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
import android.R
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView


class HostsFragment : Fragment() {
    private val viewModel: SharedTripViewModel by activityViewModels()
    private lateinit var hostsAdapter:HostsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        hostsAdapter = HostsAdapter(viewModel.hostPresenters.value!!)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = hostsAdapter
        }

        hostsAdapter.onItemClick = { view ->
            val extras = FragmentNavigatorExtras(view to "transition")
            val nav = Navigation.findNavController(requireActivity(), com.example.wellcome.R.id.nav_host_fragment)
            val directions = HostsFragmentDirections.navigateToHostDetails()
            nav.navigate(directions, extras)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        postponeEnterTransition()
        val binding = FragmentHostsBinding
            .inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}