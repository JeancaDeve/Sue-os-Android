package com.codycod.dreamsreservation.models.reserva

import com.codycod.dreamsreservation.models.factura.Factura
import com.codycod.dreamsreservation.models.habitacion.Habitaciones
import com.codycod.dreamsreservation.models.huesped.Huesped

data class Reservas(
    val fechaReserva:String,
    val fechaEntrada :String,
    val habitacion : Habitaciones,
    val fechaSalida : String,
    val factura : Factura,
    val huesped: Huesped //borrar luego
)