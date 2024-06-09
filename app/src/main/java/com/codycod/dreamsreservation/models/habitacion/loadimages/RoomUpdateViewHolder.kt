package com.codycod.dreamsreservation.models.habitacion.loadimages

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R

class RoomUpdateViewHolder(inflate: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflate.inflate(R.layout.item_image_room, viewGroup, false)){
        private var routeImage:ImageView? = null

    init {
        routeImage = itemView.findViewById(R.id.image_room_item)
    }

    fun bind(image:Int){
        routeImage?.setImageResource(image)
    }

    }