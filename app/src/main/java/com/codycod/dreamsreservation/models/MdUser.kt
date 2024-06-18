package com.codycod.dreamsreservation.models

import com.codycod.dreamsreservation.enums.EnUserRoles
import java.io.Serializable

data class MdUser(
    val name: String,
    val lastname: String,
    val dni: String,
    val phone: String,
    val role: EnUserRoles
) : Serializable{
    val code: Long = 0
}
