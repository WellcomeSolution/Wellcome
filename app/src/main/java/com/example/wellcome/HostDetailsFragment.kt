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
import android.animation.ObjectAnimator
import android.widget.LinearLayout
import android.animation.PropertyValuesHolder
import android.animation.Animator
import android.view.animation.*
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.example.services.HostPresenter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class HostDetailsFragment : Fragment() {
    private val viewModel: HostViewModel by navGraphViewModels(R.id.hostFragment)
    private val hostPicture = "http://10.0.2.2:5229"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

        viewModel.profilePictureUrl.observe(viewLifecycleOwner, { url : String? ->
            if(!url.isNullOrEmpty()) {
                val pictureUrl = "${hostPicture}${viewModel.profilePictureUrl.value}"
                Picasso.get()
                    .load(pictureUrl)
                    .into(profile_image, object: com.squareup.picasso.Callback {
                        override fun onSuccess() {
                            viewModel.isLoading.value = false
                            val bottomUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.bottom_up)
                            bottom_menu.startAnimation(bottomUpAnimation)
                            bottom_menu.visibility = View.VISIBLE
                        }

                        override fun onError(e: java.lang.Exception?) {
                            //do smth when there is picture loading error
                        }
                    })
            }
        })

        book.setOnClickListener{
            val modalBottomSheet = HostRequestBottomSheet()
            modalBottomSheet.show(childFragmentManager, HostRequestBottomSheet.TAG)
        }
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

   /*     val mapFragment = childFragmentManager.findFragmentById(R.id.maptrip) as SupportMapFragment
        mapFragment.getMapAsync(this)*/

        val binding = FragmentHostDetailsBinding
            .inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
   /* override fun onMapReady(googleMap: GoogleMap) {
        val appointLoc = LatLng(lat, lng)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(appointLoc))
        googleMap.addMarker(
            MarkerOptions().position(appointLoc).title("Marker")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(appointLoc,15f))
    }*/
}

