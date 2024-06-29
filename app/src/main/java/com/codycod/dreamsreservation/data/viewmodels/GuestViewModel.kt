package com.codycod.dreamsreservation.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codycod.dreamsreservation.data.models.MdGuest
import com.codycod.dreamsreservation.utils.Functions
import com.google.firebase.firestore.FirebaseFirestore

class GuestViewModel : ViewModel() {
    private val firebase = FirebaseFirestore.getInstance()
    private val collection = firebase.collection("guest")
    private val guestDni = MutableLiveData<MdGuest?>()

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

    fun getGuestByDni(dni: String) {
        collection
            .whereEqualTo("dni", dni)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val guestDocument = documents.documents[0].data

                    val guest = guestDocument?.let { Functions.parseGuestJson(it) }

                    guestDni.value = guest

                }
            }
    }


}