package com.codycod.dreamsreservation.data.models

import java.io.Serializable

data class MdReview(
    val user: MdUser,
    val comment: String,
    val datetime: String
): Serializable
