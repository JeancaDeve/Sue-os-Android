package com.codycod.dreamsreservation.data.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codycod.dreamsreservation.data.models.MdReservation
import com.google.firebase.firestore.FirebaseFirestore

class ReservationsViewModel: ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    val reservationStatus = MutableLiveData<Boolean>()
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


}