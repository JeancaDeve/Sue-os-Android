package com.codycod.dreamsreservation.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.data.models.MdBill
import com.codycod.dreamsreservation.data.models.MdGuest
import com.codycod.dreamsreservation.data.models.MdReservation
import com.codycod.dreamsreservation.data.models.MdRoom
import com.codycod.dreamsreservation.data.models.MdUser
import com.codycod.dreamsreservation.data.viewmodels.BillViewModel
import com.codycod.dreamsreservation.data.viewmodels.GuestViewModel
import com.codycod.dreamsreservation.data.viewmodels.ReservationsViewModel
import com.codycod.dreamsreservation.data.viewmodels.RoomsViewModel
import com.codycod.dreamsreservation.utils.Functions
import java.time.LocalDate

class FormReservationActivity : AppCompatActivity() {

    // view models required
    private lateinit var guestViewModel: GuestViewModel
    private lateinit var billViewModel: BillViewModel
    private lateinit var roomViewModel: RoomsViewModel
    private lateinit var reservationViewModel: ReservationsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_reservation_user)


        //init if view model
        guestViewModel = ViewModelProvider(this)[GuestViewModel::class.java]
        billViewModel = ViewModelProvider(this)[BillViewModel::class.java]
        reservationViewModel = ViewModelProvider(this)[ReservationsViewModel::class.java]
        roomViewModel = ViewModelProvider(this)[RoomsViewModel::class.java]

        //user to reservation
        var userReservation: MdUser? = null

        //references of buttons in the items
        val btnCancel = findViewById<Button>(R.id.btn_exit_form_reserva)
        val btnConfirm = findViewById<Button>(R.id.btn_confirmar_reserva)

        //references input text fields
        val edtDni = findViewById<EditText>(R.id.txt_dni)
        val edtPhone = findViewById<EditText>(R.id.txt_phone_number)
        val edtEntryDate = findViewById<EditText>(R.id.txt_entry_date)
        val edtDepartureDate = findViewById<EditText>(R.id.txt_departure_date)

        //reference of checkbox
        val checkGuest = findViewById<CheckBox>(R.id.check_im_guest)

        //this is the user logged
        val userLogged = Functions.getUserInfo(this)

        //to return to before activity
        btnCancel.setOnClickListener {
            finish()
        }

        checkGuest.setOnClickListener {
            if (checkGuest.isChecked) {
                edtDni.setText(userLogged.dni)
                edtPhone.setText(userLogged.phone)
                userReservation = userLogged
            } else {
                edtDni.setText("")
                edtPhone.setText("")
                userReservation = null
            }

            edtDni.isEnabled = !checkGuest.isChecked
            edtPhone.isEnabled = !checkGuest.isChecked
        }

        //to make a reservation
        btnConfirm.setOnClickListener {

            if (intent.hasExtra("room_reservation")) {
                val roomReservation = intent.getSerializableExtra("room_reservation") as MdRoom

                //get data from edit text

                val dniValue = edtDni.text
                val phoneValue = edtPhone.text
                val entryDateValue = edtEntryDate.text
                val departureDateValue = edtDepartureDate.text


                val guest = userReservation?.let {
                    MdGuest(
                        name = userLogged.name,
                        dni = userLogged.dni,
                        phone = userLogged.phone,
                        lastname = userLogged.lastname,
                        cantReservation = 1
                    )
                } ?: MdGuest(
                    name = "Invitado",
                    dni = "00000000",
                    phone = "000000000",
                    lastname = "Invitado",
                    cantReservation = 0
                )

                val bill = MdBill(
                    date = "2015-33-43",
                    amount = 2444f,
                    ruc = "00000000",
                )

                val reservation = MdReservation(
                    dateReservation = LocalDate.now().toString(),
                    entryDate = entryDateValue.toString().replace('/','-'),
                    room = roomReservation,
                    departureDate = departureDateValue.toString().replace('/','-'),
                    mdBill = bill,
                    guest = guest
                )

                reservationViewModel.createReservation(reservation, guestViewModel, billViewModel)
            }


        }


    }
}