package com.codycod.dreamsreservation.activities.customer.habitaciones

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.models.habitacion.Habitaciones
import com.codycod.dreamsreservation.models.habitacion.HabitacionesXAdapter


class HabitacionesActivity: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms_list_user)

        val rvMatri = findViewById<RecyclerView>(R.id.rv_habitaciones_matri)
        val rvSuites = findViewById<RecyclerView>(R.id.rv_suites)
        val rvIndiv = findViewById<RecyclerView>(R.id.rv_individuales)

        val listHabitaciones = listOf<Habitaciones>(
            Habitaciones("Una habitación es un espacio cerrado dentro de una vivienda, usado para dormir, trabajar o relajarse, con muebles y decoración según su propósito.","$50",R.drawable.habitacion1, "Piso 4"),
            Habitaciones("Una habitación es un cuarto en una casa destinado a actividades específicas, equipado con muebles y decorado según su función.","$60",R.drawable.habitacion2, "Piso 4"),
            Habitaciones("Una habitación es un espacio cerrado en una vivienda, usado para dormir o trabajar, con muebles y decoración adecuados a su uso.","$100",R.drawable.habitacion3, "Piso 4"),
            Habitaciones("Una habitación es un espacio cerrado dentro de una vivienda, destinado a diversas actividades y amueblado según su función.","$40",R.drawable.habitacion4, "Piso 4")
        )

        val listHabitaciones2 = listOf<Habitaciones>(
            Habitaciones("Una habitación es un espacio cerrado dentro de una vivienda, usado para dormir, trabajar o relajarse, con muebles y decoración según su propósito.","$50",R.drawable.habitacion1, "Piso 4"),
            Habitaciones("Una habitación es un cuarto en una casa destinado a actividades específicas, equipado con muebles y decorado según su función.","$60",R.drawable.habitacion2, "Piso 4"),
            Habitaciones("Una habitación es un espacio cerrado en una vivienda, usado para dormir o trabajar, con muebles y decoración adecuados a su uso.","$100",R.drawable.habitacion3, "Piso 4"),
            Habitaciones("Una habitación es un espacio cerrado dentro de una vivienda, destinado a diversas actividades y amueblado según su función.","$40",R.drawable.habitacion4, "Piso 4")
        )

        val listHabitaciones3 = listOf<Habitaciones>(
            Habitaciones("Una habitación es un espacio cerrado dentro de una vivienda, usado para dormir, trabajar o relajarse, con muebles y decoración según su propósito.","$50",R.drawable.habitacion1, "Piso 4"),
            Habitaciones("Una habitación es un cuarto en una casa destinado a actividades específicas, equipado con muebles y decorado según su función.","$60",R.drawable.habitacion2, "Piso 4"),
            Habitaciones("Una habitación es un espacio cerrado en una vivienda, usado para dormir o trabajar, con muebles y decoración adecuados a su uso.","$100",R.drawable.habitacion3, "Piso 4"),
            Habitaciones("Una habitación es un espacio cerrado dentro de una vivienda, destinado a diversas actividades y amueblado según su función.","$40",R.drawable.habitacion4, "Piso 4")
        )

        rvMatri.adapter = HabitacionesXAdapter(listHabitaciones)

        rvMatri.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        rvSuites.adapter = HabitacionesXAdapter(listHabitaciones2)
        rvSuites.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)


        rvIndiv.adapter = HabitacionesXAdapter(listHabitaciones3)
        rvIndiv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }
}