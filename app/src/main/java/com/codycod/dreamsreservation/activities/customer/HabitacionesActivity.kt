package com.codycod.dreamsreservation.activities.customer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuInflater
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.activities.customer.reservas.MisReservasActivity
import com.codycod.dreamsreservation.enums.EstadoHabitacion
import com.codycod.dreamsreservation.models.habitacion.Habitaciones
import com.codycod.dreamsreservation.models.habitacion.HabitacionesXAdapter


class HabitacionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms_list_user)

        val rvMatri = findViewById<RecyclerView>(R.id.rv_habitaciones_matri)
        val rvSuites = findViewById<RecyclerView>(R.id.rv_suites)
        val rvIndiv = findViewById<RecyclerView>(R.id.rv_individuales)

        val listHabitaciones = listOf<Habitaciones>(

            Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            ),
            Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            ),Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            )
            ,Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            )

        )

        val listHabitaciones2 = listOf<Habitaciones>(
            Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            ),Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            ),Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            )
        )

        val listHabitaciones3 = listOf<Habitaciones>(
            Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            ),Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            ),Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            ),Habitaciones(
                R.string.large_text_example,
                100f,
                listOf(R.drawable.habitacion1, R.drawable.habitacion2, R.drawable.habitacion3),
                4,
                "Una cama",
                200,
                EstadoHabitacion.DISPONIBLE
            )
        )

        rvMatri.adapter = HabitacionesXAdapter(listHabitaciones)

        rvMatri.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        rvSuites.adapter = HabitacionesXAdapter(listHabitaciones2)
        rvSuites.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        rvIndiv.adapter = HabitacionesXAdapter(listHabitaciones3)
        rvIndiv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        //funcion para abrir el menu de opciones

        val btnMenu = findViewById<ImageButton>(R.id.btn_menu_open)

        btnMenu.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Te presento las opciones : )")
            builder.setMessage("😎")

            // Set up the buttons
            builder.setPositiveButton("Ubicar") { dialog, which ->
                openGoogleMaps(37.7749, -122.4194)
            }
            builder.setNegativeButton("Info de Habitacion") { dialog, which ->
               startActivity(Intent(this, InfoHabitacionActivity::class.java))
            }
            builder.setNeutralButton("Mis Reservas") { dialog, which ->
                startActivity(Intent(this, MisReservasActivity::class.java))
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }


    }

    private fun openGoogleMaps(latitude: Double, longitude: Double): Boolean {
        val uri = "geo:$latitude,$longitude"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
            return true
        } else {
            // Maneja el caso en que no hay una aplicación para manejar el intent
            Toast.makeText(this, "En proceso", Toast.LENGTH_SHORT).show()
            return false
        }
    }


    /* private fun  openMenu(view : View){
 val popup = PopupMenu(this, view)

         val inflater : MenuInflater = popup.menuInflater
         inflater.inflate(R.layout.menu_user, popup.menu)



     }*/


}