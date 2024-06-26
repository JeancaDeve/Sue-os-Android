package com.codycod.dreamsreservation.data.models

data class MdReservation(
    val dateReservation: String,
    val entryDate: String,
    val room: MdRoom,
    val departureDate: String,
    val mdBill: MdBill,
    val guest: MdGuest //delete later
)
