package com.codycod.dreamsreservation.models.guest

data class MdGuest(
   val name : String,
   val lastname : String,
   val dni : String,
   val phone : String,
   //val reservations : ArrayList<MdReservation>
){
   val code : Long = 1
}
