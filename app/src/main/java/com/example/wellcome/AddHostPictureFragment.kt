package com.example.wellcome

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import com.example.wellcome.data.CreateTripViewModel
import com.example.wellcome.databinding.FragmentAddHostAddressBinding
import com.example.wellcome.databinding.FragmentAddHostPictureBinding
import kotlinx.android.synthetic.main.fragment_add_host_address.*
import kotlinx.android.synthetic.main.fragment_add_host_picture.*
import kotlinx.android.synthetic.main.fragment_add_host_picture.next_button

class AddHostPictureFragment : Fragment() {
    private val viewModel: CreateTripViewModel by navGraphViewModels(R.id.servicesFragment)
    private val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val image = result.data?.data
            selected_image.setImageURI(image)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image_picker.setOnClickListener{
            val pickPhoto = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startForResult.launch(pickPhoto)
        }

        next_button.setOnClickListener{
            val directions = ServicesFragmentDirections.navigateToAddDescriptions()
            nav.navigate(directions)
        }
        prev_button_picture.setOnClickListener{
            nav.popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddHostPictureBinding.inflate(
            inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}