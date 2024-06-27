package com.codycod.dreamsreservation.utils

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.codycod.dreamsreservation.data.enums.EnRoomStatus
import com.codycod.dreamsreservation.data.enums.EnTypeRoom
import com.codycod.dreamsreservation.data.enums.EnUserRoles
import com.codycod.dreamsreservation.data.models.MdResponseApiReniec
import com.codycod.dreamsreservation.utils.functions.contentexample.ContentExample
import com.codycod.dreamsreservation.data.models.MdRoom
import com.codycod.dreamsreservation.data.models.MdUser
import com.codycod.dreamsreservation.data.viewmodels.DniViewModel
import java.time.Duration
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


        //to list by type room

        fun listRoomsByType(type: EnTypeRoom? = null): List<MdRoom> {
            return if (type == null) ContentExample.roomsList
            else ContentExample.roomsList.filter { room ->
                room.typeRoom == type
            }
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

        fun parseRoomJson(dataJson: MutableMap<String, Any>): MdRoom {

            /*
                  val userReview = MdUser(
                      dni = "12345678",
                      name = "Kevin",
                      role = EnUserRoles.COMMON_USER,
                      phone = "987654321",
                      lastname = "Fernandez"
                  )

                  val reviewsJson = dataJson["reviews"] as? ArrayList<Map<String, Any>> ?: emptyList()

                  val reviews  = reviewsJson.map { reviewJson ->
                      MdReview(
                          user = userReview,
                          comment = reviewJson["comment"] as String,
                          datetime = reviewJson["datetime"] as String
                      )
                  }
            */


            val roomModel = MdRoom(
                image = dataJson["image"] as? List<String> ?: emptyList(),
                typeRoom = EnTypeRoom.valueOf(dataJson["typeRoom"] as String),
                number = (dataJson["number"] as? Number)?.toShort() ?: 0,
                price = (dataJson["price"] as? Number)?.toFloat() ?: 0f,
                content = dataJson["content"] as? List<String> ?: emptyList(),
                description = dataJson["description"] as? String ?: "",
                floor = (dataJson["floor"] as? Number)?.toShort() ?: 0,
                status = EnRoomStatus.valueOf(dataJson["status"] as String),
                reviews = ArrayList()
            )

            return roomModel
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


    }


}


