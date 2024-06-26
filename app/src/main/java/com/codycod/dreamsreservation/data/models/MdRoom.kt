package com.codycod.dreamsreservation.data.models

import com.codycod.dreamsreservation.data.enums.EnRoomStatus
import com.codycod.dreamsreservation.data.enums.EnTypeRoom
import java.io.Serializable

data class MdRoom(
    val description: String,
    val price: Float,
    val image: List<String>,
    val floor: Short,
    val content: List<String>,
    val number: Short,
    val status: EnRoomStatus,
    val typeRoom: EnTypeRoom,
    var reviews: ArrayList<MdReview>
) : Serializable
