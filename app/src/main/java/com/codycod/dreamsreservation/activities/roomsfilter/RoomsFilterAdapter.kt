package com.codycod.dreamsreservation.activities.roomsfilter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.models.room.MdRoom

class RoomsFilterAdapter(private val listRoomsFilter: List<MdRoom>) :
    RecyclerView.Adapter<RoomsFilterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsFilterViewHolder =
        RoomsFilterViewHolder(LayoutInflater.from(parent.context), parent)


    override fun getItemCount(): Int = listRoomsFilter.size

    override fun onBindViewHolder(holder: RoomsFilterViewHolder, position: Int) =
        holder.bind(listRoomsFilter[position])

}
