package com.codycod.dreamsreservation.activities.admin.Reservas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.functions.Functions
import com.codycod.dreamsreservation.models.reserva.Reservas
import java.time.format.DateTimeFormatter

class ReservasViewHolder (inflater: LayoutInflater,ViewGroup:ViewGroup):
RecyclerView.ViewHolder(inflater.inflate(R.layout.item_reservas,ViewGroup,false)) {
    private var txtfecha: TextView? = null
    private var textHuesped: TextView? = null
    private var txtDiasHospedado: TextView? = null
    private var txtNHabitacion: TextView? = null
    private var txtMontoTotal: TextView? = null

        init {
            txtfecha=itemView.findViewById(R.id.txtFecha)
            textHuesped=itemView.findViewById(R.id.txtHuesped)
            txtDiasHospedado=itemView.findViewById(R.id.txtDiasHospedado)
            txtNHabitacion=itemView.findViewById(R.id.txtNHabitacion)
            txtMontoTotal=itemView.findViewById(R.id.txtMontoTotal)
        }
    fun bind (reservas: Reservas){

        val daysHosted = Functions().getDayHosted(reservas.fechaEntrada,reservas.fechaSalida)

        txtfecha?.text = reservas.fechaReserva
        textHuesped?.text = "${reservas.huesped.name} ${reservas.huesped.lastname}"
        txtDiasHospedado?.text = daysHosted.toString()
        txtNHabitacion?.text = reservas.habitacion.numero.toString()
        txtMontoTotal?.text = "S/${(reservas.habitacion.precio * daysHosted)}"
    }
}