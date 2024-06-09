package com.codycod.dreamsreservation.models.habitacion

import com.codycod.dreamsreservation.enums.EstadoHabitacion

data class Habitaciones(
    val descripcion: Int, //cambiar a string luego
    val precio: Float,
    val imgHabitacion: List<Int>, //cambiar a string luego
    val piso: Short,
    val contenido : String,
    val numero : Short,
    val estado : EstadoHabitacion,

    )



