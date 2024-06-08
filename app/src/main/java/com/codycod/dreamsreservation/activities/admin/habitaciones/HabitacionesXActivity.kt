package com.codycod.dreamsreservation.activities.admin.habitaciones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.models.habitacion.Habitaciones
import com.codycod.dreamsreservation.models.habitacion.HabitacionesXAdapter

class HabitacionesXActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitaciones)

        val recyclerHabitaciones = findViewById<RecyclerView>(R.id.recyclerHabitaciones)


        val listHabitaciones = listOf<Habitaciones>(
            Habitaciones(
                "Descubre nuestras acogedoras habitaciones de hotel, equipadas con comodidades modernas para una estancia confortable.  ",
                "Piso :4",
                R.drawable.habitacion1,
                "$ 340.00"
            ),
            Habitaciones(
                "Descubre nuestras acogedoras habitaciones de hotel, equipadas con comodidades modernas para una estancia confortable.  ",
                "Piso :1",
                R.drawable.habitacion2,
                "$ 120.00"
            ),
            Habitaciones(
                "Descubre nuestras acogedoras habitaciones de hotel, equipadas con comodidades modernas para una estancia confortable.  ",
                "Piso :3",
                R.drawable.habitacion3,
                "$ 640.00"
            ),
            Habitaciones(
                "Descubre nuestras acogedoras habitaciones de hotel, equipadas con comodidades modernas para una estancia confortable.  ",
                "Piso :5",
                R.drawable.habitacion4,
                "$ 840.00"
            ),
            Habitaciones(
                "Descubre nuestras acogedoras habitaciones de hotel, equipadas con comodidades modernas para una estancia confortable.  ",
                "Piso :7",
                R.drawable.habitacion5,
                "$ 940.00"
            )
        )
        val adapter = HabitacionesXAdapter(listHabitaciones)
        recyclerHabitaciones.adapter = adapter
        recyclerHabitaciones.layoutManager = LinearLayoutManager(this)
    }
}