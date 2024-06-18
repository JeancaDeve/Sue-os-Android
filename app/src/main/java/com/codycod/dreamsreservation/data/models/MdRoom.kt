package com.codycod.dreamsreservation.data.models

import com.codycod.dreamsreservation.data.enums.EnRoomStatus
import com.codycod.dreamsreservation.data.enums.EnTypeRoom
import java.io.Serializable

data class MdRoom(
    val description: Int, //change to string later
    val price: Float,
    val image: String,
    val floor: Short,
    val content: String,
    val number: Short,
    val status: EnRoomStatus,
    val typeRoom: EnTypeRoom,
    var reviews: ArrayList<MdReview>
) : Serializable {
    val code: Long = 0
}



