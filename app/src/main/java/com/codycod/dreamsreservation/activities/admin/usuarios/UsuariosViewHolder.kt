package com.codycod.dreamsreservation.activities.admin.usuarios

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.activities.admin.usuarios.Usuarios


class UsuariosViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_usuarioadmi, viewGroup, false)) {
    private var txtNombre: TextView? = null
    private var txtApellidos: TextView? = null
    private var txtSusReservas: TextView? = null
    private var txtOtrasReservas: TextView? = null
    private var txtMontoTotal: TextView? = null

    init {
        txtNombre = itemView.findViewById(R.id.txtNombre)
        txtApellidos = itemView.findViewById(R.id.txtApellidos)
        txtSusReservas = itemView.findViewById(R.id.txtSusReservas)
        txtOtrasReservas = itemView.findViewById(R.id.txtOtrasReservas)
        txtMontoTotal = itemView.findViewById(R.id.txtMontoTotal)
    }

    fun bind(usuarios: Usuarios) {
        txtNombre?.text = usuarios.nombre
        txtApellidos?.text = usuarios.apellidos
        txtSusReservas?.text = usuarios.reservas.toString()
        txtOtrasReservas?.text = usuarios.otrosreservas.toString()
        txtMontoTotal?.text = usuarios.montototal.toString()
    }
}

