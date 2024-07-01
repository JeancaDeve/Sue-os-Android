package com.codycod.dreamsreservation.utils

class Messages {
    companion object {
        const val INVALID_PHONE = "El número celular no es válido :("
        const val INVALID_DNI = "El número de dni no es válido :("
        fun welcome(name: String) = "Bienvendi@ $name"
        const val PHONE_REQUIRED = "Porfavor ingrese el número de celular"
        const val DNI_REQUIRED = "Porfavor ingrese el número de dni"
        const val USER_EXIST = "El usuario ya existe :("
        const val DATES_EMPTY = "Falta ingresar fechas"
        const val DATES_INVALID = "La fecha de entrada no puede ser posterior a la de salida"
        const val ENTRY_INVALID = "La fecha de entrada no puede ser anterior a la actual"
    }
}