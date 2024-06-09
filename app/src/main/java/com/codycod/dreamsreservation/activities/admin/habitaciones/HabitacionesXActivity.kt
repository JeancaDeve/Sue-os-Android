package com.codycod.dreamsreservation.activities.admin.habitaciones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.enums.EstadoHabitacion
import com.codycod.dreamsreservation.models.habitacion.Habitaciones
import com.codycod.dreamsreservation.models.habitacion.HabitacionesXAdapter

class HabitacionesXActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitaciones)

        val recyclerHabitaciones = findViewById<RecyclerView>(R.id.recyclerHabitaciones)


        val listHabitaciones = listOf<Habitaciones>(
            Habitaciones(
                R.string.large_text_example,
                332.43f,
                R.drawable.habitacion1,
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            ),
            Habitaciones(
                R.string.large_text_example,
                332.43f,
                R.drawable.habitacion1,
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            ),Habitaciones(
                R.string.large_text_example,
                332.43f,
                R.drawable.habitacion1,
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            ),
            Habitaciones(
                R.string.large_text_example,
                332.43f,
                R.drawable.habitacion1,
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            )
        )
        val adapter = HabitacionesXAdapter(listHabitaciones)
        recyclerHabitaciones.adapter = adapter
        recyclerHabitaciones.layoutManager = LinearLayoutManager(this)
    }
}