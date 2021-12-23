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
import androidx.recyclerview.widget.RecyclerView


class HostsFragment : Fragment() {
    private val viewModel: SharedTripViewModel by activityViewModels()
    private lateinit var hostsAdapter:HostsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hostsAdapter = HostsAdapter(viewModel.hosts.value!!)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = hostsAdapter
        }

        recyclerView.doOnPreDraw { startPostponedEnterTransition() }
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