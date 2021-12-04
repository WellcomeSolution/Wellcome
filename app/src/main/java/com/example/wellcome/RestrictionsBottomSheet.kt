package com.example.wellcome.com.example.wellcome

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.*
import com.example.wellcome.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RestrictionsBottomSheet : BottomSheetDialogFragment() {
    private lateinit var behaviour : BottomSheetBehavior<View>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        dialog.setOnKeyListener { arg0, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                //behaviour.state = BottomSheetBehavior.STATE_COLLAPSED
                dialog.dismiss()
            }
            true
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.restrictions_bottom_sheet_content, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }
}
