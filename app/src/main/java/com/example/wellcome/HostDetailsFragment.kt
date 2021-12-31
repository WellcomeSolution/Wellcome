package com.example.wellcome

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.example.wellcome.data.HostViewModel
import com.example.wellcome.databinding.FragmentHostDetailsBinding
import com.example.wellcome.databinding.FragmentHostsBinding
import com.example.wellcome.utils.themeColor
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.android.synthetic.main.fragment_host_details.*
import android.animation.LayoutTransition

import android.widget.LinearLayout




class HostDetailsFragment : Fragment() {
    private val hostViewModel: HostViewModel by navGraphViewModels(R.id.hostFragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //host_details.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.reply_motion_duration_medium).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }

        val binding = FragmentHostDetailsBinding
            .inflate(inflater, container, false)
        binding.viewModel = hostViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}