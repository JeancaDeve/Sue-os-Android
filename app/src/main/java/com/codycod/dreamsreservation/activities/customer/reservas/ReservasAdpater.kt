package com.codycod.dreamsreservation.activities.customer.reservas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.models.reserva.Reservas

class ReservasAdapter (val list: List<Reservas>)
    : RecyclerView.Adapter<ReservasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservasViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        return ReservasViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ReservasViewHolder, position: Int) {
        val reservas = list[position]
        holder.bind(reservas)
    }
}