package com.codycod.dreamsreservation.utils

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.codycod.dreamsreservation.data.enums.EnRoomStatus
import com.codycod.dreamsreservation.data.enums.EnTypeRoom
import com.codycod.dreamsreservation.data.enums.EnUserRoles
import com.codycod.dreamsreservation.data.models.MdBill
import com.codycod.dreamsreservation.data.models.MdGuest
import com.codycod.dreamsreservation.data.models.MdReservation
import com.codycod.dreamsreservation.data.models.MdResponseApiReniec
import com.codycod.dreamsreservation.data.models.MdReview
import com.codycod.dreamsreservation.utils.functions.contentexample.ContentExample
import com.codycod.dreamsreservation.data.models.MdRoom
import com.codycod.dreamsreservation.data.models.MdUser
import com.codycod.dreamsreservation.data.viewmodels.DniViewModel
import com.codycod.dreamsreservation.data.viewmodels.UserViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoUnit

class FunctionsData {
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

        //to get information to user using api reniec
        fun withInformationUserWithDni(
            dniNumber: String,
            phone: String,
            viewModel: DniViewModel,
            lifecycleOwner: LifecycleOwner,
            callback: (MdUser?) -> Unit
        ) {
            viewModel.fetchDniData("apis-token-8772.gtR7nYBjQE8WiVlObpqlVpWdwxax128c", dniNumber)
            val observer = object : Observer<MdResponseApiReniec?> {
                override fun onChanged(value: MdResponseApiReniec?) {
                    viewModel.dniData.removeObserver(this)

                    if (value != null) {
                        val user = MdUser(
                            dni = value.numeroDocumento,
                            name = value.nombres,
                            lastname = "${value.apellidoPaterno} ${value.apellidoMaterno}",
                            phone = phone,
                            role = EnUserRoles.COMMON_USER,
                        )
                        callback(user)
                    } else {
                        callback(null)
                    }

                }

            }
            viewModel.dniData.observe(lifecycleOwner, observer)
        }

        //convert a array to string
        fun arrayToString(list: List<String>): String {
            var text: String = ""

            for (s in list) {
                text += "$s - "
            }
            return text
        }

        //to parse data of json in object MdRoom

        fun parseRoomJson(
            dataJson: MutableMap<String, Any>,
            viewModel: UserViewModel,
            context: LifecycleOwner
        ): MdRoom {

            val roomModel = MdRoom(
                image = dataJson["image"] as? List<String> ?: emptyList(),
                typeRoom = EnTypeRoom.valueOf(dataJson["typeRoom"] as String),
                number = (dataJson["number"] as? Number)?.toShort() ?: 0,
                price = (dataJson["price"] as? Number)?.toFloat() ?: 0f,
                content = dataJson["content"] as? List<String> ?: emptyList(),
                description = dataJson["description"] as? String ?: "",
                floor = (dataJson["floor"] as? Number)?.toShort() ?: 0,
                status = EnRoomStatus.valueOf(dataJson["status"] as String),
                reviews = getReviewsRooms(dataJson, viewModel, context)
            )

            return roomModel
        }

        private fun getReviewsRooms(
            dataJson: MutableMap<String, Any>,
            viewModel: UserViewModel,
            context: LifecycleOwner
        ): ArrayList<MdReview> {

            val reviews = ArrayList<MdReview>()

            val reviewsData = dataJson["reviews"] as? List<Map<String, Any>> ?: emptyList()

            var userReview: MdUser? = null

            for (review in reviewsData) {

                viewModel.getUserByDni(review["userDni"] as String)

                viewModel.userDni.observe(context, Observer {
                    userReview = it
                })

                reviews.add(
                    MdReview(
                        comment = review["comment"] as String,
                        datetime = review["datetime"] as String,
                        user = userReview ?: MdUser(
                            "Anónimo",
                            "",
                            "00000000",
                            "987654321",
                            EnUserRoles.COMMON_USER
                        )
                    )
                )


            }
            return reviews

        }


        //to convert a json in a reservation model

        fun parseReservationJson(
            dataJson: MutableMap<String, Any>
        ): MdReservation {

            val mdBill = MdBill("00-00-0000", "98765432112", 0f)

            val guest = MdGuest("", "", "", "", 1)

            val room = ContentExample.roomsList[0]

            return MdReservation(
                dateReservation = dataJson["dateReservation"] as String,
                entryDate = dataJson["entryDate"] as String,
                departureDate = dataJson["departureDate"] as String,
                mdBill = mdBill,
                guest = guest,
                room = room
            )
        }


        //to convert a json in a guest model

        fun parseGuestJson(dataJson: MutableMap<String, Any>): MdGuest {
            return MdGuest(
                name = dataJson["name"] as String,
                lastname = dataJson["lastname"] as String,
                dni = dataJson["dni"] as String,
                phone = dataJson["phone"] as String,
                cantReservation = (dataJson["cantReservation"] as Int).toShort(),

                )
        }


        fun parseUserJson(dataJson: MutableMap<String, Any>): MdUser {
            return MdUser(
                role = EnUserRoles.valueOf(dataJson["role"] as String),
                dni = dataJson["dni"] as String,
                phone = dataJson["phone"] as String,
                name = dataJson["name"] as String,
                lastname = dataJson["lastname"] as String
            )
        }

        //to verify phone number is valid for peru
        fun invalidPhone(phone: String): Boolean {
            return !(phone.length >= 9 && phone.all { it.isDigit() })
        }

        // to verify dni is valid for peru
        fun invalidDni(dni: String): Boolean {
            return !(dni.length >= 8 && dni.all { it.isDigit() })
        }

        //custom toast|

        fun customToast(
            message: String,
            context: Context,
            duration: Int = Toast.LENGTH_SHORT
        ) =
            Toast.makeText(context, message, duration).show()


        //to save info of user to show info in others activities
        fun saveInfoUser(user: MdUser, context: Context) {
            val sharedPref = context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("nameUser", user.name)
                putString("lastnameUser", user.lastname)
                putString("phoneUser", user.phone)
                putString("dniUser", user.dni)
                apply()
            }
        }

        //to get info user saved in shared preferences
        fun getUserInfo(context: Context): MdUser {
            val sharedPreferences =
                context.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            val name = sharedPreferences?.getString("nameUser", "Username")
            val lastname = sharedPreferences?.getString("lastnameUser", "Lastname")
            val dni = sharedPreferences?.getString("dniUser", "DNI")
            val phone = sharedPreferences?.getString("phoneUser", "Phone")

            return MdUser(
                name = name!!,
                lastname = lastname!!,
                dni = dni!!,
                phone = phone!!,
                role = EnUserRoles.COMMON_USER
            )

        }

        //to verify if a date is valida

        fun verifyDate(entry: String, departure: String): String? {
            val dateNow = LocalDate.now()

            if (entry.isEmpty() || departure.isEmpty()) {
                return Messages.DATES_EMPTY
            }

            return try {
                val entryLocal = LocalDate.parse(entry)
                val departureLocal = LocalDate.parse(departure)

                when {
                    entryLocal.isBefore(dateNow) -> Messages.ENTRY_INVALID
                    entryLocal.isAfter(departureLocal) -> Messages.DATES_INVALID
                    else -> null
                }
            } catch (e: DateTimeParseException) {
                "Una o ambas fechas no son válidas."
            }
        }


    }


}


