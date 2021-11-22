package com.example.wellcome
import android.annotation.SuppressLint
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
import androidx.room.Room
import com.example.wellcome.utils.db.AppDatabase
import com.example.wellcome.utils.db.Assistance
import com.example.wellcome.utils.searchAddress
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.assistance_card_view.view.*

class AssistanceAdapter(private val dataSet: List<Assistance>):
    RecyclerView.Adapter<AssistanceAdapter.ViewHolder>()
{
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.title_assistance
        val address: TextView = itemView.address
        val callButton: Button = itemView.call_button_assistance
        val assistanceButton: Button = itemView.consulter_button_assistance
        val addfavoritesButton:Button = itemView.add_favorites_assitance
        val chipGroup: ChipGroup = itemView.chipGroup
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssistanceAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.assistance_card_view, parent, false)

        context = parent.context

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        dataSet[position].tags.forEach {
            val chip = Chip(context)
            chip.text = it
            viewHolder.chipGroup.addView(chip)
        }

        viewHolder.title.text = dataSet[position].title
        val city = dataSet[position].address.country?.administrativeArea?.locality?.addressLine
        val country = dataSet[position].address.country?.addressLine
        viewHolder.address.text = "$city " +
                "in $country"

        val phone  = dataSet[position].phone

        viewHolder.callButton.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel:$phone")
            context.startActivity(intent)
        }

        viewHolder.assistanceButton.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("id",dataSet[position].id)
            val intent = Intent(context, ActivityConsultAssistance::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        viewHolder.addfavoritesButton.setOnClickListener {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "wellcome"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

            db.assistanceDao().update(true,dataSet[position].id)
        }
    }

    override fun getItemCount() = dataSet.size
}