package com.codycod.dreamsreservation.models.reservation

import com.codycod.dreamsreservation.models.bill.MdBill
import com.codycod.dreamsreservation.models.room.MdRoom
import com.codycod.dreamsreservation.models.guest.MdGuest

data class MdReservation(
    val dateReservation:String,
    val entryDate :String,
    val room : MdRoom,
    val departureDate : String,
    val mdBill : MdBill,
    val guest: MdGuest //borrar luego
){
    val code : Long = 1
}
