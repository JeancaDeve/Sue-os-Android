package com.codycod.dreamsreservation.models

import com.codycod.dreamsreservation.enums.EnUserRoles

data class MdUser(
    val name: String,
    val lastname: String,
    val dni: String,
    val phone: String,
    val role: EnUserRoles
) {
    val code: Long = 1
}
