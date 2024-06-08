package com.codycod.dreamsreservation.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.activities.admin.habitaciones.HabitacionesXActivity
import com.codycod.dreamsreservation.activities.admin.updateroom.RoomUpdateActivity
import com.codycod.dreamsreservation.activities.customer.FormReservationActivity
import com.codycod.dreamsreservation.activities.customer.habitaciones.HabitacionesActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AlertDialog.Builder(this)
            .setCancelable(false)
            .setMessage("Al iniciar sesión, estaremos registrando tu información en nuestra base de datos.")
            .setNegativeButton("Aceptar") { dialog, which ->
                dialog.dismiss()
            }
            .show()

        setContentView(R.layout.activity_login)

        // Asegúrate de que estos IDs coincidan con los del archivo XML
        val edtcelular = findViewById<EditText>(R.id.edtcelular)
        val edtDni = findViewById<EditText>(R.id.edtDni)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val celular = edtcelular.text.toString()
            val dni = edtDni.text.toString()



            when {
                celular.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Por favor, ingrese su número de celular",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                dni.isEmpty() -> {
                    Toast.makeText(this, "Por favor, ingrese su DNI", Toast.LENGTH_SHORT).show()
                }

                !isValidCelular(celular) -> {
                    Toast.makeText(this, "Número de celular o DNI incorrectos", Toast.LENGTH_SHORT)
                        .show()
                }

                !isValidDni(dni) -> {
                    Toast.makeText(this, "DNI incorrecto", Toast.LENGTH_SHORT).show()
                }

                celular == "987654321" && dni == "87654321" -> {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HabitacionesActivity::class.java)
                    startActivity(intent)
                }

                celular == "987654322" && dni == "87654322" -> {
                    startActivity(Intent(this, HabitacionesXActivity::class.java))
                }

                else -> {
                    Toast.makeText(this, "Número de celular o DNI incorrectos", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    // Función para validar el número de celular
    private fun isValidCelular(celular: String): Boolean {
        return celular.length >= 9 && celular.all { it.isDigit() }
    }

    // Función para validar el DNI
    private fun isValidDni(dni: String): Boolean {
        return dni.length >= 8 && dni.all { it.isDigit() } // DNI en Perú tiene 8 dígitos
    }
}
