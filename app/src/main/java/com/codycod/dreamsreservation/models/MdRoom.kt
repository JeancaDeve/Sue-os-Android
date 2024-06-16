package com.codycod.dreamsreservation.models

import com.codycod.dreamsreservation.enums.EnRoomStatus
import com.codycod.dreamsreservation.enums.EnTypeRoom
import java.io.Serializable

data class MdRoom(
    val description: Int, //change to string later
    val price: Float,
    val image: String,
    val floor: Short,
    val content: String,
    val number: Short,
    val status: EnRoomStatus,
    val typeRoom: EnTypeRoom
) : Serializable {
    val code: Long = 1
}



