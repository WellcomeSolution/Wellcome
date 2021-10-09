package com.example.wellcome

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.wellcome.utils.WellcomeDbContext

abstract class BaseFragment : Fragment() {
    protected lateinit var dbContext: WellcomeDbContext

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbContext = WellcomeDbContext(context)
    }
}