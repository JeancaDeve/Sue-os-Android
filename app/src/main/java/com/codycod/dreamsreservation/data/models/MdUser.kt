package com.codycod.dreamsreservation.data.models

import com.codycod.dreamsreservation.data.enums.EnUserRoles
import java.io.Serializable

data class MdUser(
    val name: String,
    val lastname: String,
    val dni: String,
    val phone: String,
    val role: EnUserRoles
) : Serializable