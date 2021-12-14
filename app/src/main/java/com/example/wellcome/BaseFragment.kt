package com.example.wellcome

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.wellcome.db.AppDatabase

abstract class BaseFragment : Fragment() {
    protected lateinit var db: AppDatabase

    override fun onAttach(context: Context) {
        super.onAttach(context)
        db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "wellcome"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }
}