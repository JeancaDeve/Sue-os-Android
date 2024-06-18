package com.codycod.dreamsreservation.data.models

import com.codycod.dreamsreservation.data.enums.EnTypeRoom

data class MdButtonTypeRoom(
    val text: String, val typeRoom: EnTypeRoom?, var isActive: Boolean

)
