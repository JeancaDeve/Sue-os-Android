package com.codycod.dreamsreservation.data.models

data class MdGuest(
    val name: String,
    val lastname: String,
    val dni: String,
    val phone: String,
    //val reservations : ArrayList<MdReservation>
) {
    val code: Long = 0
}
