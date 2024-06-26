package com.codycod.dreamsreservation.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.ui.activities.InformationRoomActivity
import com.codycod.dreamsreservation.data.models.MdRoom
import com.codycod.dreamsreservation.ui.viewholders.RoomsListViewHolder

class RoomsListAdapter() :
    RecyclerView.Adapter<RoomsListViewHolder>() {


    private var list = emptyList<MdRoom>()


    fun setRooms(rooms: List<MdRoom>) {
        this.list = rooms
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RoomsListViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RoomsListViewHolder, position: Int) {
        val room = list[position]

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