package com.codycod.dreamsreservation.activities.admin.updateroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.models.habitacion.loadimages.RoomUpdateAdapter

class RoomUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_room_admin)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_images_update)

        val listRoutes = listOf<Int>(
            R.drawable.example_room_2,
            R.drawable.example_room
        )

        val adapter = RoomUpdateAdapter(listRoutes)

        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }
}