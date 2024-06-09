package com.codycod.dreamsreservation.activities.customer.reservas


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.enums.EstadoHabitacion
import com.codycod.dreamsreservation.models.factura.Factura
import com.codycod.dreamsreservation.models.habitacion.Habitaciones
import com.codycod.dreamsreservation.models.huesped.Huesped
import com.codycod.dreamsreservation.models.reserva.Reservas

class MisReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_reservas_user)
        val recyclerView = findViewById<RecyclerView>(R.id.MisReservasRecyclerView)

        //room example

        val roomExample = Habitaciones(
            R.string.large_text_example,
            100f,
            listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
            4,
            "Una cama",
            200,
            EstadoHabitacion.DISPONIBLE
        )
        val facturaExample = Factura("no tiene")

        val guestExample = Huesped("Kevin","Fernandez","87654321","987654321","holamundo_43")


        val listReservas = listOf(
            Reservas("2024-09-26", "2024-09-26", roomExample, "2024-09-27", facturaExample,guestExample),
            Reservas("2023-02-14", "2023-02-14", roomExample, "2024-09-26", facturaExample, guestExample),
            Reservas("2021-08-26", "2021-08-26", roomExample, "2024-09-26", facturaExample,guestExample),
            Reservas("2022-11-17", "2022-11-17", roomExample, "2024-09-26", facturaExample,guestExample),
            Reservas("2024-10-12", "2024-10-12", roomExample, "2024-09-26", facturaExample,guestExample)

        )


        val adapter = ReservasAdapter(listReservas)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


}