package com.codycod.dreamsreservation.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.activities.adapters.ReservationsAdapter
import com.codycod.dreamsreservation.functions.contentexample.ContentExample

class MyReservationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_reservas_user)
        val recyclerView = findViewById<RecyclerView>(R.id.MisReservasRecyclerView)


        val adapter = ReservationsAdapter(ContentExample.reservationsList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}