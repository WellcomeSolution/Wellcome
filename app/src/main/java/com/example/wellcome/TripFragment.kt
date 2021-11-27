package com.example.wellcome

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_trip.*
import com.google.android.material.transition.MaterialContainerTransform


class TripFragment : Fragment() {
    private var listener: OnBottomSheetCallbacks? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedElementEnterTransition = MaterialContainerTransform().setDuration(300L)
        sharedElementReturnTransition = MaterialContainerTransform().setDuration(300L)

        editText_restrictions.setOnClickListener{
            val intent = Intent(context, RescrictionsFormActivity::class.java)
            val options =  ActivityOptions.makeSceneTransitionAnimation(
                activity,
                editText_restrictions,
                "shared_element_container_restrictions"  // The transition name to be matched in Activity B.
            )
            activity?.startActivity(intent, options.toBundle())
        }

        editText_dates.setOnClickListener{
            val intent = Intent(context, DatesActivity::class.java)
            val options =  ActivityOptions.makeSceneTransitionAnimation(
                activity,
                editText_dates,
                "shared_element_container_dates"  // The transition name to be matched in Activity B.
            )
            activity?.startActivity(intent, options.toBundle())
        }

        editText_location.setOnClickListener{
            val intent = Intent(context, SearchCityActivity::class.java)
            val options =  ActivityOptions.makeSceneTransitionAnimation(
                activity,
                editText_location,
                "shared_element_container_location"  // The transition name to be matched in Activity B.
            )
            activity?.startActivity(intent, options.toBundle())
        }

        super.onViewCreated(view, savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trip, container, false)
    }
}