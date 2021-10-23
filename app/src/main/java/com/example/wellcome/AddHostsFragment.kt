package com.example.wellcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_hosts.*

class AddHostsFragment : BaseFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        containedButton.setOnClickListener{
            //dbContext.insertHosting(retrieveHost())
            clearTextEdit()
            showSnackBar()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_hosts, container, false)
    }

    private fun showSnackBar(){
        Snackbar.make(contextView,  R.string.host_added, Snackbar.LENGTH_SHORT)
            .setAction("Close") {
                Snackbar.make(contextView, "", Snackbar.LENGTH_SHORT)
                    .dismiss()
            }
            .show()
    }

    private fun clearTextEdit(){
        firstName.text?.clear()
        lastName.text?.clear()
        phone.text?.clear()
        email.text?.clear()
        address.text?.clear()
    }

    /*private fun retrieveHost():Hosting
        = Hosting(lastName.text.toString(), firstName.text.toString(), address.text.toString(), email.text.toString(), phone.text.toString())*/
}