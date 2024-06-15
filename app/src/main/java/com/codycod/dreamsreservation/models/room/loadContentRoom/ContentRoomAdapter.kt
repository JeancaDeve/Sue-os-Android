package com.codycod.dreamsreservation.models.room.loadContentRoom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContentRoomAdapter(private val containList : List<String>) :
RecyclerView.Adapter<ContentRoomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRoomViewHolder {
      return  ContentRoomViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: ContentRoomViewHolder, position: Int) {
        holder.bind(containList[position])
    }

    override fun getItemCount(): Int {
       return containList.size
    }
}