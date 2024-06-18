package com.codycod.dreamsreservation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.ui.viewholders.ImagesRoomViewHolder

class ImagesRoomAdapter(private val listImages: List<String>) :
    RecyclerView.Adapter<ImagesRoomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesRoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImagesRoomViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ImagesRoomViewHolder, position: Int) {
        val image = listImages[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return listImages.size
    }
}