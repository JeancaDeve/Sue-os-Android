package com.codycod.dreamsreservation.activities.admin.Reservas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.codycod.dreamsreservation.R
import java.time.LocalDate

class ReservasActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        val recyclerReservas = findViewById<RecyclerView>(R.id.recyclerReservas)

            val  listReservas = listOf<Reservas>(
                Reservas(LocalDate.of(2024, 3, 3), "Jesus Araujo Lazaro", 40, 401, 17775.00),
                Reservas(LocalDate.of(2024, 1, 12), "Kevin Fernandez Risco", 40, 401, 13335.00),
                Reservas(LocalDate.of(2024, 4, 9), "Axel Davalos Sanchez", 40, 401, 124188.00),
                Reservas(LocalDate.of(2024, 5, 7), "David Rodriguez Ulloa", 40, 401, 145.00),
            )
        val adapter = ReservasAdapter(listReservas)
        recyclerReservas.adapter = adapter
        recyclerReservas.layoutManager = LinearLayoutManager(this)
    }
}