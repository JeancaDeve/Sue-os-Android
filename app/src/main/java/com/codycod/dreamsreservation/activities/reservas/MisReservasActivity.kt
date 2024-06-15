package com.codycod.dreamsreservation.activities.reservas


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.enums.EnRoomStatus
import com.codycod.dreamsreservation.functions.exampleslist.ListExample
import com.codycod.dreamsreservation.models.bill.MdBill
import com.codycod.dreamsreservation.models.room.MdRoom
import com.codycod.dreamsreservation.models.guest.MdGuest
import com.codycod.dreamsreservation.models.reservation.MdReservation

class MisReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_reservas_user)
        val recyclerView = findViewById<RecyclerView>(R.id.MisReservasRecyclerView)




        val adapter = ReservasAdapter(ListExample.listReservas)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}