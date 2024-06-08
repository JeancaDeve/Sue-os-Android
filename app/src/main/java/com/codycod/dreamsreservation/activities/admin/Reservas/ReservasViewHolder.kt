package com.codycod.dreamsreservation.activities.admin.Reservas

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.activities.admin.Reservas.Reservas
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
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        txtfecha?.text = reservas.fecha.format(dateFormatter)
        textHuesped?.text = reservas.huesped
        txtDiasHospedado?.text = reservas.diasHospedado.toString()
        txtNHabitacion?.text = reservas.nhabitacion.toString()
        txtMontoTotal?.text = reservas.montototal.toString()
    }
}