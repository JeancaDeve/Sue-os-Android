package com.codycod.dreamsreservation.models.room.loadimages

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.functions.Functions

class RoomUpdateViewHolder(inflate: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflate.inflate(R.layout.item_image_room, viewGroup, false)){
        private var routeImage = itemView.findViewById<ImageView>(R.id.image_room_item)


    fun bind(image:String){
        Glide.with(itemView.context)
            .load(image)
            .into(routeImage)
    }

    }