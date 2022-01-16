package com.example.wellcome

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.FragmentModifyTripLocalisationBinding
import com.example.wellcome.repository.City
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.android.synthetic.main.top_app_bar.*
import kotlinx.android.synthetic.main.trip_localisation.*

class ModifyTripLocalisationFragment : Fragment() {
    private val viewModel: SharedTripViewModel by activityViewModels()
    private lateinit var cityAdapter : CityAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        initRecyclerView()
        initListeners()

        viewModel.cities.observe(viewLifecycleOwner, { cities : ArrayList<City> ->
            cityAdapter.cities.clear()
            cityAdapter.cities.addAll(cities)
            cityAdapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        searchBar.visibility = View.INVISIBLE
        top_app_bar.menu.findItem(R.id.clear).isVisible = false
        super.onDestroyView()
    }

    private fun dismiss(){
        val nav = findNavController()
        nav.popBackStack()
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

    private fun initRecyclerView(){
        cityAdapter = CityAdapter(viewModel.cities.value!!)

        recyclerView.apply {
            layoutManager= LinearLayoutManager(context)
            adapter= cityAdapter
        }

        cityAdapter.onItemClick = { city ->
            viewModel.city.value = city
            dismiss()
        }
    }

    private fun initLayout(){
        searchBar.visibility = View.VISIBLE
        top_app_bar.menu.findItem(R.id.clear).isVisible = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        val binding = FragmentModifyTripLocalisationBinding
            .inflate(inflater, container, false)
        binding.tripLocalisation.viewModel = viewModel
        binding.tripLocalisation.lifecycleOwner = this
        return binding.root
    }
}