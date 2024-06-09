package com.codycod.dreamsreservation.activities.customer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.enums.EstadoHabitacion
import com.codycod.dreamsreservation.models.habitacion.Habitaciones
import com.codycod.dreamsreservation.models.habitacion.loadcompletinformation.ContainRoomAdapter
import com.codycod.dreamsreservation.models.habitacion.loadimages.RoomUpdateAdapter


class InfoHabitacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_habitacion)


        //buttons

        val btnCancel = findViewById<Button>(R.id.btnCancelar)
        val btnIrReservar = findViewById<Button>(R.id.btnIrReservar)

        btnCancel.setOnClickListener {
            finish()
        }

        btnIrReservar.setOnClickListener {
            startActivity(Intent(this, FormReservationActivity::class.java))
        }


        //load of images

        val recycleImgHabitacionhuesped =
            findViewById<RecyclerView>(R.id.recycleImgHabitacionhuesped)


        val roomExample = Habitaciones(
            R.string.large_text_example,
            100f,
            listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
            4,
            "Una cama-Un Tubo-TV 100'-Sillón Tantrico-2 Almohadas",
            200,
            EstadoHabitacion.DISPONIBLE
        )

        val adapter = RoomUpdateAdapter(roomExample.imgHabitacion)
        recycleImgHabitacionhuesped.adapter = adapter
        recycleImgHabitacionhuesped.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        //load of contain


        val rvContain = findViewById<RecyclerView>(R.id.rv_contain_room)

        val adapterContain = ContainRoomAdapter(roomExample.contenido.split('-'))

        rvContain.adapter = adapterContain

        rvContain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


    }
}