package com.example.wellcome

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.example.services.HostPresenter
import kotlinx.android.synthetic.main.fragment_lesson.*
import com.example.wellcome.data.SharedLessonViewModel
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.databinding.FragmentLessonBinding
import com.example.wellcome.databinding.FragmentTripBinding
import com.google.android.material.transition.MaterialSharedAxis

class LessonFragment : Fragment(){
    private val viewModel: SharedLessonViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.hostPresenters.observe(viewLifecycleOwner, { hosts : ArrayList<HostPresenter> ->
            if(viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED){
                if(!hosts.isNullOrEmpty()){
                    val nav = Navigation.findNavController(requireActivity(), com.example.wellcome.R.id.nav_host_fragment)
                    val directions = NavigationFragmentDirections.navigateToLessons()
                    nav.navigate(directions)
                    viewModel.isLoading.value = false
                }
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLessonBinding.inflate(
            inflater, container, false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}

