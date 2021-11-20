package com.example.wellcome
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wellcome.utils.db.Lesson
import kotlinx.android.synthetic.main.cours_card_view.view.*

class LessonAdapter(private val dataSet: List<Lesson>):
    RecyclerView.Adapter<LessonAdapter.ViewHolder>()
{
    private lateinit var context:Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.title_lesson
        val phone: TextView = itemView.phone_lesson
        val callButton: Button = itemView.call_button_lesson
        val consultButton: Button = itemView.consulter_button_lesson
        val country: TextView = itemView.country_lesson
        val department: TextView = itemView.department_lesson
        val city: TextView = itemView.city_lesson
        val postalCode: TextView =itemView.postalcode_lesson
        val address: TextView = itemView.address_lesson
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cours_card_view, parent, false)

        context = parent.context

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = dataSet[position].title
        viewHolder.phone.text = dataSet[position].phone
        viewHolder.country.text = dataSet[position].address.country?.addressLine
        viewHolder.department.text = dataSet[position].address.country?.administrativeArea?.addressLine
        viewHolder.city.text = dataSet[position].address.country?.administrativeArea?.locality?.addressLine
        viewHolder.postalCode.text = dataSet[position].address.country?.administrativeArea?.locality?.postalCode?.addressLine
        viewHolder.address.text = dataSet[position].address.country?.administrativeArea?.locality?.thoroughfare?.addressLine
        val tele = viewHolder.phone.text

        viewHolder.callButton.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$tele")
            context.startActivity(intent)
        }

        viewHolder.consultButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id",dataSet[position].id)
            val intent = Intent(context,ActivityConsultLesson::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size
}