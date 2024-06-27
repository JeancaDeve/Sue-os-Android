package com.codycod.dreamsreservation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.data.models.MdUser
import com.codycod.dreamsreservation.data.viewmodels.DniViewModel
import com.codycod.dreamsreservation.data.viewmodels.RegisterUserViewModel
import com.codycod.dreamsreservation.data.viewmodels.UserViewModel
import com.codycod.dreamsreservation.utils.functions.Functions


class LoginActivity : AppCompatActivity() {

    private lateinit var fireBaseViewModel: RegisterUserViewModel
    private lateinit var dniViewModel: DniViewModel
    private lateinit var userViewModel: UserViewModel

    companion object {
        private var userLogin: MdUser? = null
    }

    //this user that is logged in

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        splashScreen.setKeepOnScreenCondition { false }


        //init of viewmodel
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
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


    //* verify this method --- fail --- register multiple users
    private fun authorization(phone: String, dni: String) {

        //this is a new activity main

        val intent = Intent(this, MenuCustomerActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        //get user
        userViewModel.getUserByDniAndPhone(dni, phone)

        userViewModel.user.observe(this, Observer { user ->
            userLogin = user
            //when the user with set information not exist
            if (userLogin == null) {
                //create a new user
                createUser(dni, phone)

                fireBaseViewModel.userIsRegister.observe(this, Observer {
                    if (it) {
                        startActivity(intent)
                    }
                })

            } else {
                startActivity(intent)
            }
        })


    }

    //to insert user in firestore
    private fun createUser(dni: String, phone: String) {

        Functions.withInformationUserWithDni(
            dni,
            phone,
            dniViewModel,
            this
        ) { user ->
            user?.let { fireBaseViewModel.registerUser(it) }
            Toast.makeText(this, "Bienvenido ${user?.name}", Toast.LENGTH_SHORT).show()
        }


    }


}
