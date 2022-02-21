package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_services.*
import kotlinx.android.synthetic.main.services_top_bar.*
import kotlinx.android.synthetic.main.top_app_bar.*

class ServicesFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var tab1 = topbar_layout.getTabAt(0)
        var tab2 = topbar_layout.getTabAt(1)
        var tab3 = topbar_layout.getTabAt(2)
        val manager = childFragmentManager
        val transition = manager.beginTransaction()
        transition.add(R.id.nav_service_fragment,TripFragment()).commit()
        tab1?.view?.setOnClickListener{
            val manager = childFragmentManager
            val transition = manager.beginTransaction()
            transition.replace(R.id.nav_service_fragment,TripFragment()).commit()
        }
        tab2?.view?.setOnClickListener{
            val manager = childFragmentManager
            val transition = manager.beginTransaction()
            transition.replace(R.id.nav_service_fragment,LessonFragment()).commit()
        }
        tab3?.view?.setOnClickListener{
            val manager = childFragmentManager
            val transition = manager.beginTransaction()
            transition.replace(R.id.nav_service_fragment,HelpFragment()).commit()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_services, container, false)

    }

}