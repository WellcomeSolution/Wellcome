package com.example.wellcome.com.example.wellcome

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellcome.CityAdapter
import com.example.wellcome.R
import com.example.wellcome.TripFragment
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.LocalisationBottomSheetContentBinding
import com.example.wellcome.databinding.RestrictionsBottomSheetContentBinding
import com.example.wellcome.repository.City
import com.example.wellcome.utils.scaleDown
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_search_city.*
import kotlinx.android.synthetic.main.restrictions_bottom_sheet_content.*
import kotlinx.android.synthetic.main.top_app_bar.*

class LocalisationBottomSheet : BottomSheetDialogFragment() {
    private val viewModel: SharedTripViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private lateinit var behaviour : BottomSheetBehavior<View>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        initClickListeners()

        val cityAdapter = CityAdapter(viewModel.cities.value!!)

        recyclerView.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= cityAdapter
        }

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                viewModel.cities.value!!.clear()
                viewModel.updateCities(cs.toString())
            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
            override fun afterTextChanged(arg0: Editable) {}
        })

         top_app_bar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.clear -> {
                    searchBar.text.clear()
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                behaviour = BottomSheetBehavior.from(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }


    private fun initClickListeners(){
        top_app_bar.setOnClickListener{
            this.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = LocalisationBottomSheetContentBinding
            .inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
