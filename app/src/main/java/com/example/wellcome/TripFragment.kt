package com.example.wellcome

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.android.synthetic.main.fragment_trip.*
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale


class TripFragment : Fragment() {
    private var listener: OnBottomSheetCallbacks? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        editText_restrictions.setOnClickListener{
            val intent = Intent(context, RescrictionsActivityForm::class.java)
            val options =  ActivityOptions.makeSceneTransitionAnimation(
                activity,
                editText_restrictions,
                "shared_element_container"  // The transition name to be matched in Activity B.
            )
            activity?.startActivity(intent, options.toBundle())
        }

        editText_dates.setOnClickListener{
            val intent = Intent(context, DatesActivity::class.java)
            val options =  ActivityOptions.makeSceneTransitionAnimation(
                activity,
                editText_restrictions,
                "shared_element_container"  // The transition name to be matched in Activity B.
            )
            activity?.startActivity(intent, options.toBundle())
        }

        super.onViewCreated(view, savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().setDuration(300L)
        sharedElementReturnTransition = MaterialContainerTransform().setDuration(300L)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trip, container, false)
    }
}