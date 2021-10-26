package com.example.wellcome

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.wellcome.utils.WellcomeDbContext

abstract class BaseFragment : Fragment() {
    protected lateinit var db: AppDatabase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "wellcome"
        ).build()
    }
}