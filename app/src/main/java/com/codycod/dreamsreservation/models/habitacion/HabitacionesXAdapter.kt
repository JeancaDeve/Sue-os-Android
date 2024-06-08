package com.codycod.dreamsreservation.models.habitacion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HabitacionesXAdapter(private  val list: List<Habitaciones>):RecyclerView.Adapter<HabitacionesXViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitacionesXViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  HabitacionesXViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HabitacionesXViewHolder, position: Int) {
        val habitaciones = list[position]
        holder.bind(habitaciones)
    }
}