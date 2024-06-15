package com.codycod.dreamsreservation.models.room.loadContentRoom

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R

class ContentRoomViewHolder(inflater : LayoutInflater, viewGroup: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_contain_room, viewGroup , false)){
        private var textContain : TextView? = null


    init {
        textContain = itemView.findViewById(R.id.text_contain)
    }


    fun bind(contain : String){
        textContain?.text = contain
    }



    }