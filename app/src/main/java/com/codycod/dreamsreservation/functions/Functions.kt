package com.codycod.dreamsreservation.functions

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

open class Functions {
     fun getDayHosted(entryDate : String, departureDate : String) : Long{

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        val entry = LocalDate.parse(entryDate, formatter)
        val departure = LocalDate.parse(departureDate, formatter)

        return ChronoUnit.DAYS.between(entry,departure)

    }
}