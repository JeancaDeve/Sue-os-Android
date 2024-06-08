package com.codycod.dreamsreservation.activities.admin.Reservas


import java.time.LocalDate

data class Reservas(

    val fecha: LocalDate,
    val huesped: String,
    val diasHospedado: Int,
    val nhabitacion: Int,
    val montototal: Double
)