package com.codycod.dreamsreservation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.data.models.MdRoomPartial
import com.codycod.dreamsreservation.ui.viewholders.SavedRoomViewHolder

class SavedRoomAdapter() : RecyclerView.Adapter<SavedRoomViewHolder>() {

    private var savedRoomList = emptyList<MdRoomPartial>()

    //to add registers and savedRoomList and notifier change
    fun setSaveRoom(listSavedRoom: List<MdRoomPartial>) {
        this.savedRoomList = listSavedRoom
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedRoomViewHolder {
        return SavedRoomViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int = savedRoomList.size

    override fun onBindViewHolder(holder: SavedRoomViewHolder, position: Int) {
        holder.bindSavedRoom(savedRoomList[position])
    }
}