package com.codycod.dreamsreservation.models.room.loadimages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RoomUpdateAdapter(private val listImages: List<String>) :
    RecyclerView.Adapter<RoomUpdateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomUpdateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RoomUpdateViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RoomUpdateViewHolder, position: Int) {
        val image = listImages[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return listImages.size
    }
}