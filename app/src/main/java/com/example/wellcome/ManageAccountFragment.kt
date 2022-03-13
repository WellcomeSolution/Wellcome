package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.wellcome.data.UserViewModel
import com.example.wellcome.databinding.FragmentManageAccountBinding

import android.app.AlertDialog
import kotlinx.android.synthetic.main.fragment_manage_account.*
import android.content.DialogInterface
import androidx.navigation.Navigation
import com.example.wellcome.utils.PopError
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_manage_account.prev_button_address


class ManageAccountFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private val popError: PopError = PopError()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        prev_button_address.setOnClickListener{

            when {
                userViewModel.firstName.value?.let { it1 -> popError.stringTypeTest(it1) } == true -> {
                    Snackbar.make(poperror,"Please enter your firstname!", Snackbar.LENGTH_LONG)
                        .show()
                }
                userViewModel.lastName.value?.let { it1 -> popError.stringTypeTest(it1) } == true -> {
                    Snackbar.make(poperror,"Please enter your lastName!", Snackbar.LENGTH_LONG)
                        .show()
                }
               userViewModel.gender.value == null ->  {
                    Snackbar.make(poperror,"Please enter your gender!", Snackbar.LENGTH_LONG)
                        .show()
                }
                userViewModel.phone.value?.let { it1 -> popError.stringTypeTest(it1) } == true -> {
                    Snackbar.make(poperror,"Please enter your phone number!", Snackbar.LENGTH_LONG)
                        .show()
                }
                else -> {
                     userViewModel.update()
                    nav.popBackStack()
                }
            }
        }
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