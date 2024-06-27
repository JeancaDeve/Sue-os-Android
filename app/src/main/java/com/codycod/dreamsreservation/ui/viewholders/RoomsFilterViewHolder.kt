package com.codycod.dreamsreservation.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.data.models.MdRoom

class RoomsFilterViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_room_filter, viewGroup, false)) {

    private var image: ImageView = itemView.findViewById(R.id.img_room_f)
    private var price: TextView = itemView.findViewById(R.id.price_room_f)
    private var description: TextView = itemView.findViewById(R.id.description_room_f)


    fun bind(room: MdRoom) {

        val listImages = room.image

        Glide.with(itemView.context)
            .load(listImages[0])
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .into(image)

        price.text = "S/. ${room.price}"
        description.setText(room.description)

    }

}