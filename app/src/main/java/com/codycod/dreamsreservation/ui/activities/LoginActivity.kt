package com.codycod.dreamsreservation.ui.activities

import android.content.Context
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
import com.codycod.dreamsreservation.utils.Functions
import com.codycod.dreamsreservation.utils.Messages


class LoginActivity : AppCompatActivity() {

    private lateinit var fireBaseViewModel: RegisterUserViewModel
    private lateinit var dniViewModel: DniViewModel
    private lateinit var userViewModel: UserViewModel


    private var userLogin: MdUser? = null


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
                phone.isEmpty() -> Functions.customToast(Messages.PHONE_REQUIRED, this)

                dni.isEmpty() -> Functions.customToast(Messages.DNI_REQUIRED, this)

                Functions.invalidPhone(phone) -> Functions.customToast(Messages.INVALID_PHONE, this)

                Functions.invalidDni(dni) -> Functions.customToast(Messages.INVALID_DNI, this)

                else -> authorization(phone, dni)
            }
        }
    }


    //* verify this method --- fail --- register multiple users
    private fun authorization(phone: String, dni: String) {
        //this is a new activity main
        val intent = Intent(this, MenuCustomerActivity::class.java)

        //to clear all before activities
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        //get user
        userViewModel.getUserByDniAndPhone(dni, phone)

        userViewModel.user.observe(this, Observer { user ->
            userLogin = user
            //when the user with set information not exist
            if (userLogin == null) {
                //create a new user
                createUser(dni, phone)
                //observe if userRegistered is true or false
                fireBaseViewModel.userIsRegister.observe(this, Observer {
                    if (it) {
                        //observe user register and get information
                        fireBaseViewModel.userRegister.observe(this, Observer { userRegister ->
                            if (userRegister != null) {

                                Functions.customToast(
                                    Messages.welcome(userRegister.name),
                                    this,
                                    Toast.LENGTH_LONG
                                )

                                //save info of user logged in shared preferences
                                Functions.saveInfoUser(userRegister, this)
                                startActivity(intent)
                            }
                        })
                    }
                })

            } else {
                //save info of user logged in shared preferences
                Functions.saveInfoUser(userLogin!!, this)
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
        }


    }


}
