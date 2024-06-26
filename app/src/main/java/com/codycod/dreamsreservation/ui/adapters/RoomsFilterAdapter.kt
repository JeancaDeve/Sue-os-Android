package com.codycod.dreamsreservation.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.ui.activities.InformationRoomActivity
import com.codycod.dreamsreservation.ui.viewholders.RoomsFilterViewHolder
import com.codycod.dreamsreservation.data.models.MdRoom

class RoomsFilterAdapter() :
    RecyclerView.Adapter<RoomsFilterViewHolder>() {


    private var listRoomsFilter = emptyList<MdRoom>()

    fun setRoomsFilter(listRoomsFilter: List<MdRoom>) {
        this.listRoomsFilter = listRoomsFilter
        this.notifyDataSetChanged()
    }

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
