package com.codycod.dreamsreservation.activities.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.activities.InformationRoomActivity
import com.codycod.dreamsreservation.activities.viewholders.RoomsFilterViewHolder
import com.codycod.dreamsreservation.models.MdRoom

class RoomsFilterAdapter(private val listRoomsFilter: List<MdRoom>) :
    RecyclerView.Adapter<RoomsFilterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsFilterViewHolder =
        RoomsFilterViewHolder(LayoutInflater.from(parent.context), parent)


    override fun getItemCount(): Int = listRoomsFilter.size

    override fun onBindViewHolder(holder: RoomsFilterViewHolder, position: Int) {

        val room = listRoomsFilter[position]

        holder.bind(room)


        //to give functionality to the item button
        holder.itemView.findViewById<Button>(R.id.btn_see_more).setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(holder.itemView.context, InformationRoomActivity::class.java)

            //send the object room with name object_room
            intent.putExtra("object_room", room)
            context.startActivity(intent)
        }
    }

}
