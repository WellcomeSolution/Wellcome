package com.example.wellcome.com.example.wellcome

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellcome.CityAdapter
import com.example.wellcome.R
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.LocalisationBottomSheetContentBinding
import com.example.wellcome.repository.Address
import kotlinx.android.synthetic.main.activity_search_city.recyclerView
import kotlinx.android.synthetic.main.top_app_bar.*

class LocalisationBottomSheet : BaseBottomSheet() {
    private val viewModel: SharedTripViewModel by viewModels(ownerProducer = { requireParentFragment() })
    private lateinit var cityAdapter : CityAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        initListeners()
        initLayout()
        initRecyclerView()

        viewModel.cities.observe(this, { cities : ArrayList<Address> ->
            cityAdapter.cities.clear()
            cityAdapter.cities.addAll(cities)
            cityAdapter.notifyDataSetChanged()
        })
    }

    private fun initRecyclerView(){
        cityAdapter = CityAdapter(viewModel.cities.value!!)

        recyclerView.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= cityAdapter
        }
    }

    private fun initLayout(){
        searchBar.visibility = View.VISIBLE
        top_app_bar.menu.findItem(R.id.clear).isVisible = true
    }

    override fun onDismiss(dialog: DialogInterface) {
        searchBar.visibility = View.INVISIBLE
        top_app_bar.menu.findItem(R.id.clear).isVisible = false
        super.onDismiss(dialog)
    }

    private fun initListeners(){
        top_app_bar.setOnClickListener{
            this.dismiss()
        }

        top_app_bar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.clear -> {
                    searchBar.text.clear()
                    true
                }
                else -> false
            }
        }

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                viewModel.updateCities(cs.toString())
            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
            override fun afterTextChanged(arg0: Editable) {}
        })
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
