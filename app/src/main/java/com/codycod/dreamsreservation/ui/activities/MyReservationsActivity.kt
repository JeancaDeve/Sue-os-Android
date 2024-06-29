package com.codycod.dreamsreservation.ui.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.data.viewmodels.ReservationsViewModel
import com.codycod.dreamsreservation.ui.adapters.ReservationsAdapter
import com.codycod.dreamsreservation.utils.Functions
import com.codycod.dreamsreservation.utils.functions.contentexample.ContentExample

class MyReservationsActivity : AppCompatActivity() {


    private lateinit var reservationsViewModel: ReservationsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_reservas_user)

        reservationsViewModel = ViewModelProvider(this)[ReservationsViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.MisReservasRecyclerView)
        val adapter = ReservationsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //this is a user Logged in app
        val userLogged = Functions.getUserInfo(this)

        //loading data reservation
        reservationsViewModel.getReservationsByDniUser(userLogged.dni)
        reservationsViewModel.reservationsUser.observe(this, Observer {
            adapter.setReservations(it)
        })


    }


}