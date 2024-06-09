package com.codycod.dreamsreservation.models.habitacion.loadcompletinformation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContainRoomAdapter(private val containList : List<String>) :
RecyclerView.Adapter<ContainRoomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainRoomViewHolder {
      return  ContainRoomViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: ContainRoomViewHolder, position: Int) {
        holder.bind(containList[position])
    }

    override fun getItemCount(): Int {
       return containList.size
    }
}