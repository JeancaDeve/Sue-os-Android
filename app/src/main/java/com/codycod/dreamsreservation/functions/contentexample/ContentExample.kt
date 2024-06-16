package com.codycod.dreamsreservation.functions.contentexample

import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.enums.EnRoomStatus
import com.codycod.dreamsreservation.enums.EnTypeRoom
import com.codycod.dreamsreservation.enums.EnUserRoles
import com.codycod.dreamsreservation.models.MdBill
import com.codycod.dreamsreservation.models.MdGuest
import com.codycod.dreamsreservation.models.MdReservation
import com.codycod.dreamsreservation.models.MdRoom
import com.codycod.dreamsreservation.models.MdUser

class ContentExample {

    companion object {
        private val roomExample01 = MdRoom(
            R.string.large_text_example,
            100f,
            "https://img.freepik.com/fotos-premium/habitaciones-4_1045419-15.jpg - https://eu-central-1.linodeobjects.com/tecnohotelnews/2022/12/image004-2-1.jpg",
            4,
            "Una cama - Una TV 35'",
            200,
            EnRoomStatus.AVAILABLE,
            EnTypeRoom.MATRIMONIAL
        )

        private val roomExample02 = MdRoom(
            R.string.large_text_example,
            400f,
            "https://www.hola.com/imagenes/decoracion/20230425230358/dormitorios-inspirados-en-habitaciones-hoteles-am/1-237-29/habitaciones-hotel-6a-a.jpg - https://cdn.shopify.com/s/files/1/0417/8349/2759/files/Habitacion-Modenrna-y-minimalista_large.jpg?v=1595955799",
            4,
            "Una cama - Una TV 35' - Un sillon Tantrico - Un tubo",
            500,
            EnRoomStatus.AVAILABLE,
            EnTypeRoom.SUITES
        )


        private val mdBillExample = MdBill("2003-04-05", "12345678998", 2300.00f)

        private val userCommonExample =
            MdUser("Kevin", "Fernandez", "87654321", "987654321", EnUserRoles.COMMON_USER)

        private val userAdminExample =
            MdUser("Faraon", "Love Shady", "87654322", "987654322", EnUserRoles.ADMIN)


        private val guestExample = MdGuest("Kevin", "Fernandez", "87654321", "987654321")


        val reservationsList = listOf(
            MdReservation(
                "2024-09-26", "2024-09-26", roomExample01, "2024-09-27", mdBillExample, guestExample
            ), MdReservation(
                "2023-02-14", "2023-02-14", roomExample01, "2024-09-26", mdBillExample, guestExample
            ), MdReservation(
                "2021-08-26", "2021-08-26", roomExample01, "2024-09-26", mdBillExample, guestExample
            ), MdReservation(
                "2022-11-17", "2022-11-17", roomExample01, "2024-09-26", mdBillExample, guestExample
            ), MdReservation(
                "2024-10-12", "2024-10-12", roomExample01, "2024-09-26", mdBillExample, guestExample
            )

        )

        val roomsList = listOf(
            roomExample01, roomExample02, roomExample01, roomExample02, roomExample01, roomExample02

        )


        val userList = listOf(
            userAdminExample, userCommonExample

        )


    }

}