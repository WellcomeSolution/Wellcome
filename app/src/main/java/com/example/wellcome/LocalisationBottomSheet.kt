package com.example.wellcome

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellcome.CityAdapter
import com.example.wellcome.R
import com.example.wellcome.data.SharedTripViewModel
import com.example.wellcome.databinding.LocalisationBottomSheetContentBinding
import com.example.wellcome.repository.Address
import com.example.wellcome.repository.City
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.localisation_bottom_sheet_content.*
import kotlinx.android.synthetic.main.top_app_bar.*

import android.util.DisplayMetrics
import android.view.WindowManager







class LocalisationBottomSheet : BaseBottomSheet() {
    private val viewModel: SharedTripViewModel by activityViewModels()
    private lateinit var cityAdapter : CityAdapter


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener {
            val bottomSheet = (it as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        }

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        initListeners()
        initLayout()
        initRecyclerView()

        viewModel.cities.observe(this, { cities : ArrayList<City> ->
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

        cityAdapter.onItemClick = { city ->
            viewModel.city.value = city
            dismiss()
        }
    }

    private fun initLayout(){
        val displayMetrics = context?.resources?.displayMetrics
        val height = displayMetrics?.heightPixels
        recyclerView.minimumHeight = height!!

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

class ScreenUtils(ctx: Context) {
    var ctx: Context
    var metrics: DisplayMetrics
    val height: Int
        get() = metrics.heightPixels
    val width: Int
        get() = metrics.widthPixels
    val realHeight: Int
        get() = metrics.heightPixels / metrics.densityDpi
    val realWidth: Int
        get() = metrics.widthPixels / metrics.densityDpi
    val density: Int
        get() = metrics.densityDpi

    fun getScale(picWidth: Int): Int {
        val display = (ctx.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
            .defaultDisplay
        val width = display.width
        var `val` = (width / picWidth).toDouble()
        `val` = `val` * 100.0
        return `val`.toInt()
    }

    init {
        this.ctx = ctx
        val wm = ctx
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        metrics = DisplayMetrics()
        display.getMetrics(metrics)
    }
}
