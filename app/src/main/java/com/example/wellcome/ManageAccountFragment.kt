package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.databinding.FragmentManageAccountBinding
import com.example.wellcome.databinding.SignUpFormBottomSheetContentBinding
import android.widget.NumberPicker

import android.R
import android.app.AlertDialog
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.NumberPicker.OnValueChangeListener
import kotlinx.android.synthetic.main.fragment_manage_account.*
import android.content.DialogInterface





class ManageAccountFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edit_text_gender.showSoftInputOnFocus = false

        edit_text_gender.setOnClickListener{
            val genders = arrayOf("Male", "Female")

            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Select gender")
            builder.setItems(genders, DialogInterface.OnClickListener { dialog, which ->
                edit_text_gender.setText(genders[which])
            })
            builder.show()
        }
    }

    override fun onDestroy() {
        userViewModel.update()
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentManageAccountBinding
            .inflate(inflater, container, false)
        binding.viewModel = userViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}