package com.codycod.dreamsreservation.models

import com.codycod.dreamsreservation.enums.EnTypeRoom

data class ButtonTypeRoom(
    val text : String,
    val typeRoom : EnTypeRoom?,
    var isActive : Boolean

)
