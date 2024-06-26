package com.codycod.dreamsreservation.data.models

import com.codycod.dreamsreservation.data.enums.EnUserRoles

data class MdResponseApiReniec(
    val nombres: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val tipoDocumento: String,
    val numeroDocumento: String,
    val digitoVerificador: String
)
