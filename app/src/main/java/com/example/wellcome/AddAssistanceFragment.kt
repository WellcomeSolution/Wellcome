package com.example.wellcome

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.wellcome.models.Priority
import com.example.wellcome.utils.db.*
import kotlinx.android.synthetic.main.fragement_add_assistance.*
import kotlinx.android.synthetic.main.fragement_add_trip.*
import kotlinx.android.synthetic.main.fragment_add_lesson.*


class AddAssistanceFragment: BaseFragment()  {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragement_add_assistance, container, false)


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_image_assistance.setOnClickListener{
            val pickPhoto = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            resultLauncher.launch(pickPhoto)
        }

        assistance_add_button.setOnClickListener{

            db.assistanceDao().insert(retrieveAssistance())
            clearTextEdit()
            Toast.makeText(context,"Assistance added",Toast.LENGTH_SHORT).show()
        }
        assistance_priority.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                assistance_priority.setSelection(0)
            }

        }

    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri? = result.data?.data
            image_view_assistance.setImageURI(selectedImage)
            image_view_assistance.tag = selectedImage.toString()
        }
    }

    private fun clearTextEdit(){
        assistance_titre.text?.clear()
        assistance_phone.text?.clear()
        assistance_address.text?.clear()
        assistance_country.text?.clear()
        assistance_city.text?.clear()
        assistance_department.text?.clear()
        assistance_postalcode.text?.clear()
        assistance_description.text?.clear()
        checkbox1_assistance.isChecked = false
        checkbox2_assistance.isChecked = false
        checkbox3_assistance.isChecked = false

    }

    private fun retrieveAssistance(): Assistance {
        var mlist = mutableListOf<String>()
        if(checkbox1_assistance.isChecked){
            mlist.add(checkbox1_assistance.text.toString())
        }
        if(checkbox2_assistance.isChecked){
            mlist.add(checkbox2_assistance.text.toString())
        }
        if(checkbox3_assistance.isChecked){
            mlist.add(checkbox3_assistance.text.toString())
        }
        var list = mlist.toList()
        var ret_assistance = Assistance(false, assistance_titre.text.toString(),assistance_description.text.toString(), getAddress(),
            assistance_phone.text.toString(),0,list, true, Priority.Low, image_view_assistance.tag.toString())
        return ret_assistance
    }

    fun getAddress(): Address
            = Address(
        country = Country(
            addressLine = assistance_country.text.toString(),
            administrativeArea = AdministrativeArea(
                addressLine = assistance_department.text.toString(),locality = Locality(
                    addressLine = assistance_city.text.toString(),
                    thoroughfare = Thoroughfare(addressLine = assistance_address.text.toString()),
                    postalCode = PostalCode(addressLine = assistance_postalcode.text.toString())
                )
            )
        )
    )
}