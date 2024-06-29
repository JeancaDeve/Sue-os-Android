package com.codycod.dreamsreservation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.ui.viewholders.ReservasViewHolder
import com.codycod.dreamsreservation.data.models.MdReservation

class ReservationsAdapter :
    RecyclerView.Adapter<ReservasViewHolder>() {

    private var list = emptyList<MdReservation>()

    fun setReservations(reservations: List<MdReservation>) {
        this.list = reservations
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservasViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReservasViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ReservasViewHolder, position: Int) {
        val reservation = list[position]
        holder.bind(reservation)
    }
}