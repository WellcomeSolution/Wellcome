package com.example.wellcome

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.wellcome.utils.db.Address
import com.example.wellcome.utils.db.AppDatabase
import com.example.wellcome.utils.db.Lesson
import com.example.wellcome.utils.searchAddress
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.activity_consult_assistance_page.view.*
import java.lang.StringBuilder
import kotlinx.android.synthetic.main.activity_consult_lesson_page.view.*
class ConsultLessonAdapter(val context: Context, private val dataSet: Lesson):
    RecyclerView.Adapter<ConsultLessonAdapter.ViewHolder>() {

    lateinit var db: AppDatabase

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = itemView.image_lesson_consult
        val title: TextView = itemView.findViewById(R.id.title_lesson_con)
        val country: TextView = itemView.findViewById(R.id.country_lesson_con)
        val city: TextView = itemView.findViewById(R.id.city_lesson_con)
        val postal_code: TextView = itemView.findViewById(R.id.postal_code_con)
        val thoroughfare: TextView = itemView.findViewById(R.id.thoroughfare_con)
        val chipGroup:ChipGroup = itemView.findViewById(R.id.chipGroup_lessonGroup)
        val phone: TextView = itemView.findViewById(R.id.phone_lesson_con)
        val callButton: Button = itemView.findViewById(R.id.call_button_lesson_con)
        val addFavoriteButton: Button = itemView.findViewById(R.id.add_favorites_lesson_con)
        val description: EditText = itemView.findViewById(R.id.lesson_description_con)
        val duration: EditText = itemView.findViewById(R.id.lesson_sessionduree_con)
        val checkbox1: CheckBox = itemView.findViewById(R.id.checkbox1_lesson_con)
        val checkbox2: CheckBox = itemView.findViewById(R.id.checkbox2_lesson_con)
        val checkbox3: CheckBox = itemView.findViewById(R.id.checkbox3_lesson_con)

        val reserve: Button = itemView.findViewById(R.id.reserve)
        val addressButton: Button = itemView.findViewById(R.id.search_address)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultLessonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_consult_lesson_page, parent, false)

        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "wellcome"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        return ViewHolder(view)
    }

    private fun getAddressRepresentation(address: Address): String{
        val sb = StringBuilder()
        sb.append(address.country?.addressLine + "\n")
        sb.append(address.country?.administrativeArea?.locality?.addressLine + "\n")
        sb.append(address.country?.administrativeArea?.locality?.thoroughfare?.addressLine)
        return sb.toString()
    }

    override fun onBindViewHolder(v: ConsultLessonAdapter.ViewHolder, position: Int) {
        v.imageView.setImageURI(Uri.parse(dataSet.image))
        v.country.text = dataSet.address.country?.addressLine
        v.city.text = dataSet.address.country?.administrativeArea?.locality?.addressLine
        v.postal_code.text = dataSet.address.country?.administrativeArea?.locality?.postalCode?.addressLine
        v.thoroughfare.text = dataSet.address.country?.administrativeArea?.locality?.thoroughfare?.addressLine

        v.title.text = dataSet.title
        v.phone.text = dataSet.phone
        v.description.setText(dataSet.description)
        v.duration.setText(dataSet.sessionDuration.toString())
        if(dataSet.tags.toString().contains(v.checkbox1.text)){
            v.checkbox1.isChecked
        }
        if(dataSet.tags.toString().contains(v.checkbox2.text)){
            v.checkbox2.isChecked
        }
        if(dataSet.tags.toString().contains(v.checkbox3.text)){
            v.checkbox3.isChecked
        }

        val tele = v.phone.text
        v.callButton.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$tele")
            context.startActivity(intent)
        }

        v.reserve.isEnabled = dataSet.isAvailable

        v.reserve.setOnClickListener {
            val intent = Intent(context, LessonRequestActivity::class.java)
            context.startActivity(intent)
        }

        v.addressButton.setOnClickListener {
            context.searchAddress(getAddressRepresentation(dataSet.address))
        }

        v.addFavoriteButton.setOnClickListener{
            db.assistanceDao().update(true, dataSet.id)
        }
    }

    override fun getItemCount(): Int {
        return 1;
    }

}