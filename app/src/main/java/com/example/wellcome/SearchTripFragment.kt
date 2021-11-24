package com.example.wellcome

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wellcome.utils.db.Trip
import kotlinx.android.synthetic.main.fragement_add_trip.*
import kotlinx.android.synthetic.main.fragement_search_trip.*

class SearchTripFragment : BaseFragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragement_search_trip, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recycler_view_trip.apply {
            layoutManager= LinearLayoutManager(activity)
            adapter=TripAdapter(db.tripDao().getAll())
        }

        tags_trip.setOnClickListener{
            withMultiChoiceList(it)
        }

        search_bar_trip.setOnClickListener{
            val titleTrip = trip_titre_search.text.toString()
            recycler_view_trip.adapter = context?.let { it1
                -> TripAdapter(db.tripDao().findTripByTitle(titleTrip))}
        }
    }

    fun withMultiChoiceList(view: View) {
        val items = arrayOf("Baby", "Pet", "Child")
        val selectedList = ArrayList<Int>()
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Filters")
        builder.setMultiChoiceItems(items, null)
        { dialog, which, isChecked ->
            if (isChecked) {
                selectedList.add(which)
            } else if (selectedList.contains(which)) {
                selectedList.remove(Integer.valueOf(which))
            }
        }
        builder.setPositiveButton("Selected") { dialogInterface, i ->
            val selectedString = ArrayList<String>()
            var mlist = mutableListOf<String>()//list of tags
            for (j in selectedList.indices) {
                selectedString.add(items[selectedList[j]])
                mlist.add(items[selectedList[j]])
            }
            // Toast.makeText(context,"Items are :"+ Arrays.toString(selectedString.toTypedArray()),Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "Items are :" + mlist.toString(), Toast.LENGTH_SHORT).show()
            var listTag = mlist.toList()
            var MTrip = mutableListOf<Trip>()

            for (x in listTag) {
                if (x == "Baby") MTrip.addAll(db.tripDao().findTripIncludeBaby())
                if (x == "Pet") MTrip.addAll(db.tripDao().findTripIncludeDog())
                if (x == "Child") MTrip.addAll(db.tripDao().findTripIncludeChild())
            }
            var listtest = MTrip.distinct().toList()
            recycler_view_trip.adapter = context?.let { it -> TripAdapter(listtest) }
        }
        builder.show()
    }
}