package com.codycod.dreamsreservation.activities.customer.reservas


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.functions.Functions
import com.codycod.dreamsreservation.models.reserva.Reservas
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class ReservasViewHolder
    (inflater: LayoutInflater, viewGroup: ViewGroup)
    : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_mis_reservas_user,viewGroup,false)) {

    private var FechaReserva: TextView? = null
    private var NHabitacion: TextView? = null
    private var DiasHospedados: TextView? = null
    private var MontoTotal: TextView? = null

    init{
        FechaReserva= itemView.findViewById(R.id.FechaReserva)
        NHabitacion = itemView.findViewById(R.id.N_habitacion)
        DiasHospedados= itemView.findViewById(R.id.diasHospedado)
        MontoTotal= itemView.findViewById(R.id.montoTotal)
    }

    fun bind(reservas: Reservas){

        val daysHosted = Functions().getDayHosted(reservas.fechaEntrada,reservas.fechaSalida)

        FechaReserva?.text = reservas.fechaReserva
        NHabitacion?.text = reservas.habitacion.numero.toString()
        DiasHospedados?.text= daysHosted.toString()
        MontoTotal?.text= "S/${(reservas.habitacion.precio * daysHosted)}"
    }



}