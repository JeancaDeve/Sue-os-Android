package com.codycod.dreamsreservation.utils.functions

import com.codycod.dreamsreservation.data.enums.EnTypeRoom
import com.codycod.dreamsreservation.utils.functions.contentexample.ContentExample
import com.codycod.dreamsreservation.data.models.MdRoom
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class Functions {
    companion object {
        fun getDayHosted(entryDate: String, departureDate: String): Long {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val entry = LocalDate.parse(entryDate, formatter)
            val departure = LocalDate.parse(departureDate, formatter)
            return ChronoUnit.DAYS.between(entry, departure)
        }

        //to divider text in a list by delimiter
        fun divideText(text: String, delimiter: String = " - "): List<String> {
            return text.split(delimiter)
        }

        //to rooms list by content
        fun listRoomsByContent(filterContent: String? = null): List<MdRoom> {
            return if (filterContent.isNullOrEmpty())
                ContentExample.roomsList
            else ContentExample.roomsList.filter { room ->
                room.content.contains(
                    filterContent,
                    ignoreCase = true
                )
            }
        }

        //to list by type room

        fun listRoomsByType(type: EnTypeRoom? = null): List<MdRoom> {
            return if (type == null) ContentExample.roomsList
            else ContentExample.roomsList.filter { room ->
                room.typeRoom == type
            }
        }


    }

}

