package com.codycod.dreamsreservation.models

import java.io.Serializable

data class MdReview(
    val user: MdUser,
    val comment: String,
    val datetime: String
) : Serializable {
    val code: Long = 0
}
