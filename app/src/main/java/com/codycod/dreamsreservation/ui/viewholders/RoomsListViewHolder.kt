package com.codycod.dreamsreservation.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.utils.functions.Functions
import com.codycod.dreamsreservation.data.models.MdRoom

class RoomsListViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_room, viewGroup, false)) {

    private val imgRoom = itemView.findViewById<ImageView>(R.id.img_room_list)
    private val txtDescription = itemView.findViewById<TextView>(R.id.txt_description_list)
    private val txtPrice = itemView.findViewById<TextView>(R.id.txt_price_list)


    fun bind(mdRoom: MdRoom) {
        val listImages = Functions.divideText(mdRoom.image)

        val options = RequestOptions()
            .override(500, 500)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .centerCrop()


        Glide.with(itemView.context)
            .load(listImages[0])
            .apply(options)
            .into(imgRoom)


        txtDescription.text = mdRoom.description
        txtPrice.text = "S/.${mdRoom.price}"
    }


}


