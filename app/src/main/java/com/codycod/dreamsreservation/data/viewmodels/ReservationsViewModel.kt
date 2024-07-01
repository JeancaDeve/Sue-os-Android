package com.codycod.dreamsreservation.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codycod.dreamsreservation.data.models.MdReservation
import com.codycod.dreamsreservation.utils.FunctionsData
import com.google.firebase.firestore.FirebaseFirestore

class ReservationsViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val reservationStatus = MutableLiveData<Boolean>()
    val reservationsUser = MutableLiveData<ArrayList<MdReservation>>()
    private val collection = firestore.collection("reservations")

    fun createReservation(
        reservation: MdReservation,
        guestViewModel: GuestViewModel,
        billViewModel: BillViewModel
    ) {
        //json body
        val reservationJson = hashMapOf(
            "dateReservation" to reservation.dateReservation,
            "departureDate" to reservation.departureDate,
            "dniGuest" to reservation.guest.dni,
            "entryDate" to reservation.entryDate,
            "numberRoom" to reservation.room.number.toInt(),
            "rucBill" to reservation.mdBill.ruc
        )

        guestViewModel.createGuest(reservation.guest)

        billViewModel.createBill(reservation.mdBill)

        collection.document().set(reservationJson).addOnCompleteListener {
            reservationStatus.value = it.isSuccessful
        }

    }


    fun getReservationsByDniUser(dniUser: String) {

        val reservationsList = ArrayList<MdReservation>()

        collection.whereEqualTo("dniGuest", dniUser)
            .get()
            .addOnSuccessListener { reservations ->
                if (!reservations.isEmpty) {
                    for (reservation in reservations) {
                        val model = FunctionsData.parseReservationJson(reservation.data)

                        reservationsList.add(model)
                    }
                }
                reservationsUser.value = reservationsList

            }
    }


}