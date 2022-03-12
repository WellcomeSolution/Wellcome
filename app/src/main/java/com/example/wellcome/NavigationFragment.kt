package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.wellcome.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_navigation.*

class NavigationFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = Navigation.findNavController(requireActivity(), R.id.nav_fragment)

        if(userViewModel.isLogIn.value!!) {
            bottom_navigation.menu.findItem(R.id.page_3).isVisible = true
            bottom_navigation.menu.findItem(R.id.page_4).isVisible = false
        }
        else {
            bottom_navigation.menu.findItem(R.id.page_3).isVisible = false
            bottom_navigation.menu.findItem(R.id.page_4).isVisible = true
        }

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    nav.navigate(R.id.fragment_services)
                }
                R.id.page_2 -> {
                    nav.navigate(R.id.fragment_favorites)
                }
                R.id.page_3 -> {
                    nav.navigate(R.id.fragment_profile)
                }
                R.id.page_4 -> {
                    nav.navigate(R.id.fragment_login_menu)
                }
                R.id.page_5 -> {
                    nav.navigate(R.id.fragment_trips)
                }
            }
            true
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation, container, false)
    }

}