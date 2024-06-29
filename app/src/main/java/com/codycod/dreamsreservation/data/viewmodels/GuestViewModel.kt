package com.codycod.dreamsreservation.data.viewmodels

import androidx.lifecycle.ViewModel
import com.codycod.dreamsreservation.data.models.MdGuest
import com.google.firebase.firestore.FirebaseFirestore

class GuestViewModel : ViewModel() {
    private val firebase = FirebaseFirestore.getInstance()
    private val collection = firebase.collection("guest")

    fun createGuest(guest: MdGuest) {
        //body json

        val guestJson = hashMapOf(
            "cantReservations" to guest.cantReservation.toInt(),
            "dni" to guest.dni,
            "name" to guest.name,
            "lastname" to guest.lastname,
            "phone" to guest.phone
        )

        collection
            .document()
            .set(guestJson)

    }
}