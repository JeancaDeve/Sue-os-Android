package com.codycod.dreamsreservation.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.data.models.MdRoomSave
import com.codycod.dreamsreservation.utils.functions.Functions

class SavedRoomViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.item_saved_room,
            viewGroup,
            false
        )
    ) {


    private val imageSavedRoom = itemView.findViewById<ImageView>(R.id.img_saved_room)
    private val priceSaveRoom = itemView.findViewById<TextView>(R.id.tv_price_room_save)
    private val content = itemView.findViewById<TextView>(R.id.tv_content_room_save)


    //the request options to load images network

    private val requestOptions = RequestOptions()
        .override(500, 500)
        .error(R.drawable.error_image)
        .placeholder(R.drawable.placeholder_image)

    fun bindSavedRoom(room: MdRoomSave) {
        val firstImage = Functions.divideText(room.image)[0]

        //to load images network with Glide
        Glide.with(itemView.context)
            .load(firstImage)
            .apply(requestOptions)
            .into(imageSavedRoom)

        priceSaveRoom.text = "S/.${room.price}"
        content.text = room.content

    }


}