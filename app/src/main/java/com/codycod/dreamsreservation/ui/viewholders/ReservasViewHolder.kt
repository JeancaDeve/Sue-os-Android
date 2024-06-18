package com.codycod.dreamsreservation.ui.viewholders


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.utils.functions.Functions
import com.codycod.dreamsreservation.data.models.MdReservation


class ReservasViewHolder
    (inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_my_resrevation, viewGroup, false)) {

    private var FechaReserva: TextView? = null
    private var NHabitacion: TextView? = null
    private var DiasHospedados: TextView? = null
    private var MontoTotal: TextView? = null

    init {
        FechaReserva = itemView.findViewById(R.id.FechaReserva)
        NHabitacion = itemView.findViewById(R.id.N_habitacion)
        DiasHospedados = itemView.findViewById(R.id.diasHospedado)
        MontoTotal = itemView.findViewById(R.id.montoTotal)
    }

    fun bind(mdReservation: MdReservation) {

        val daysHosted =
            Functions.getDayHosted(mdReservation.entryDate, mdReservation.departureDate)

        FechaReserva?.text = mdReservation.dateReservation
        NHabitacion?.text = mdReservation.room.number.toString()
        DiasHospedados?.text = daysHosted.toString()
        MontoTotal?.text = "S/${(mdReservation.room.price * daysHosted)}"
    }


}