package com.codycod.dreamsreservation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.data.enums.EnUserRoles
import com.codycod.dreamsreservation.utils.functions.contentexample.ContentExample
import com.codycod.dreamsreservation.data.models.MdUser


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        splashScreen.setKeepOnScreenCondition { false }

        val edtPhone = findViewById<EditText>(R.id.edtcelular)
        val edtDni = findViewById<EditText>(R.id.edtDni)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val phone = edtPhone.text.toString()
            val dni = edtDni.text.toString()



            when {
                phone.isEmpty() -> {
                    Toast.makeText(
                        this,
                        "Porfavor, ingrese su número de celular",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                dni.isEmpty() -> {
                    Toast.makeText(this, "Por favor, ingrese su DNI", Toast.LENGTH_SHORT).show()
                }

                !isValidCelular(phone) -> {
                    Toast.makeText(this, "Número celular incorrecto", Toast.LENGTH_SHORT)
                        .show()
                }

                !isValidDni(dni) -> {
                    Toast.makeText(this, "DNI incorrecto", Toast.LENGTH_SHORT).show()
                }


                else -> authorization(phone, dni)
            }
        }
    }

    //to verify phone number is valid for peru
    private fun isValidCelular(celular: String): Boolean {
        return celular.length >= 9 && celular.all { it.isDigit() }
    }

    // to verify dni is valid for peru
    private fun isValidDni(dni: String): Boolean {
        return dni.length >= 8 && dni.all { it.isDigit() } // DNI en Perú tiene 8 dígitos
    }

    // to verify if the user exists in the list of users and verify the role
    private fun authorization(phone: String, dni: String) {

        val user: MdUser? = findUser(phone, dni)

        if (user != null) {
            when (user.role) {
                EnUserRoles.COMMON_USER ->
                    startActivity(Intent(this, MenuCustomerActivity::class.java))

                EnUserRoles.ADMIN -> Toast.makeText(
                    this,
                    "Esta cuenta es administradora",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        } else Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT)
            .show()

    }

    //to find user by phone number and dni
    private fun findUser(phone: String, dni: String): MdUser? {
        return ContentExample.userList.find { user -> user.phone == phone && user.dni == dni }
    }

}
