package com.example.wellcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.annotation.NonNull
import com.example.wellcome.R
import com.example.wellcome.repository.Address

class SingleLineItemViewHolder(@NonNull view: View?) : RecyclerView.ViewHolder(view!!) {
    val icon: ImageView
    val text: TextView

    companion object {
        @NonNull
        fun create(@NonNull parent: ViewGroup): SingleLineItemViewHolder {
            return SingleLineItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.material_list_item_single_line, parent, false)
            )
        }
    }

    init {
        icon = itemView.findViewById(R.id.mtrl_list_item_icon)
        text = itemView.findViewById(R.id.mtrl_list_item_text)
    }
}