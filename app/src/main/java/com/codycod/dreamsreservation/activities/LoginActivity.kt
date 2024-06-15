package com.codycod.dreamsreservation.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.enums.EnUserRoles
import com.codycod.dreamsreservation.functions.exampleslist.ListExample
import com.codycod.dreamsreservation.models.user.MdUser


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen  = installSplashScreen()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        splashScreen.setKeepOnScreenCondition{false}

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


                else -> authorization(celular,dni)
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

    private fun authorization(phone: String, dni: String) {

        val user: MdUser? = findUser(phone, dni)

        if (user != null) {
            when (user.role) {
                EnUserRoles.COMMON_USER ->
                    startActivity(Intent(this, MenuCustomerActivity::class.java))

                EnUserRoles.ADMIN -> Toast.makeText(this, "Corregir Mensaje", Toast.LENGTH_SHORT)
                    .show()
            }
        }else Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT)
            .show()

    }

    private fun findUser(phone: String, dni: String): MdUser? {
        return ListExample.userList.find { user -> user.phone == phone && user.dni == dni }
    }

}
