package com.codycod.dreamsreservation.models.habitacion

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R

class HabitacionesXViewHolder (inflater: LayoutInflater, viewGroup:ViewGroup):
  RecyclerView.ViewHolder(inflater.inflate(R.layout.item_habitaciones,viewGroup, false)){

      private var imgHabitaciones: ImageView? = null
      private var textDescripcion :TextView? = null
      private  var textPiso :TextView? = null
      private  var textPrecio :TextView? = null

    init {
        imgHabitaciones = itemView.findViewById(R.id.imgHabitacion)
        textDescripcion = itemView.findViewById(R.id.textDescripcion)
        textPiso = itemView.findViewById(R.id.textPiso)
        textPrecio = itemView.findViewById(R.id.textPrecio)
    }
    fun bind (habitaciones: Habitaciones){
        imgHabitaciones?.setImageResource(habitaciones.imgHabitacion)
        textDescripcion?.text =habitaciones.descripcion
        textPiso?.text =habitaciones.piso
        textPrecio?.text =habitaciones.precio


    }
}