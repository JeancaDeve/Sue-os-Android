package com.codycod.dreamsreservation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.data.enums.EnUserRoles
import com.codycod.dreamsreservation.utils.functions.contentexample.ContentExample
import com.codycod.dreamsreservation.data.models.MdUser
import com.codycod.dreamsreservation.data.viewmodels.DniViewModel
import com.codycod.dreamsreservation.data.viewmodels.RegisterUserViewModel
import com.codycod.dreamsreservation.utils.functions.Functions
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {

    private lateinit var fireBaseViewModel: RegisterUserViewModel
    private lateinit var dniViewModel: DniViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        splashScreen.setKeepOnScreenCondition { false }


        //init of viewmodel

        fireBaseViewModel = ViewModelProvider(this)[RegisterUserViewModel::class.java]
        dniViewModel = ViewModelProvider(this)[DniViewModel::class.java]


        val edtPhone = findViewById<EditText>(R.id.edtcelular)
        val edtDni = findViewById<EditText>(R.id.edtDni)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        //button for show rooms saved in new activity
        val btnSavedRooms = findViewById<ImageButton>(R.id.ib_saved_rooms)
        btnSavedRooms.setOnClickListener {
            startActivity(Intent(this, SavedRoomActivity::class.java))
        }

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


    //* verify this method --- fail --- register multiple users
    private fun authorization(phone: String, dni: String) {
        lifecycleScope.launch {
            try {
                val userExist = fireBaseViewModel.verifyExistUser(dni, phone)

                if (!userExist) {

                    //createUser(dni, phone)
                  // Log.d("auth", "Usuario Registrado")

                }else{
                    Log.d("existe","Este ya existe")
                }
                startActivity(Intent(this@LoginActivity, MenuCustomerActivity::class.java))



            } catch (_: Exception) {

            }
        }
    }

    //to insert user in firestore
    private fun createUser(dni: String, phone: String) {

        Functions.withInformationUserWithDni(
            dni,
            phone,
            dniViewModel,
            this
        ) { user ->
            fireBaseViewModel.registerUser(user)
            Toast.makeText(this, "Bienvenido ${user.name}", Toast.LENGTH_SHORT).show()
            Log.d("Create" , "suuario crearado")
        }
    }


    private fun verifyViewModel() {
        fireBaseViewModel.userRegisterStatus.observe(this, Observer { isSuceesful ->
            if (isSuceesful) {

            }
        })


    }


    //to find user by phone number and dni
    private fun findUser(phone: String, dni: String): MdUser? {
        return ContentExample.userList.find { user -> user.phone == phone && user.dni == dni }
    }

}
