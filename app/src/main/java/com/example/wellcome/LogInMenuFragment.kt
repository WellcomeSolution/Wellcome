package com.example.wellcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import kotlinx.android.synthetic.main.fragment_log_in_menu.*

class LogInMenuFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log_in_button.setOnClickListener{
            val modalBottomSheet = LogInFormBottomSheet()
            modalBottomSheet.show(childFragmentManager, LogInFormBottomSheet.TAG)
        }
        sign_up_text_view.setOnClickListener{
            val modalBottomSheet = SignUpFormBottomSheet()
            modalBottomSheet.show(childFragmentManager, SignUpFormBottomSheet.TAG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in_menu, container, false)
    }
}