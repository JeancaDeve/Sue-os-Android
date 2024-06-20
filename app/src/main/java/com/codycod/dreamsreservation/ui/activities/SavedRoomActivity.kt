package com.codycod.dreamsreservation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.data.repositories.viewmodels.RoomPartialViewModel
import com.codycod.dreamsreservation.ui.adapters.SavedRoomAdapter

class SavedRoomActivity : AppCompatActivity() {
    private lateinit var savedRoomVM: RoomPartialViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms_save)
        savedRoomVM = ViewModelProvider(this)[RoomPartialViewModel::class.java]
        //get recycler view
        val rvSavedRoom = findViewById<RecyclerView>(R.id.rv_rooms_save)

        val savedRoomAdapter = SavedRoomAdapter()
        rvSavedRoom.adapter = savedRoomAdapter
        rvSavedRoom.layoutManager = LinearLayoutManager(this)

        //set rooms for show in the recycler view
        savedRoomVM.getRoomsPartial().observe(this) { savedRooms ->
            if (savedRooms.isEmpty()) {
                savedRooms?.let {
                    savedRoomAdapter.setSaveRoom(savedRooms)
                }
            }
        }
    }
}