package com.codycod.dreamsreservation.ui.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.codycod.dreamsreservation.R

class FormReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_reservation_user)

        //buttons

        val btnCancel = findViewById<Button>(R.id.btn_exit_form_reserva)

        btnCancel.setOnClickListener {
            finish()
        }


    }
}