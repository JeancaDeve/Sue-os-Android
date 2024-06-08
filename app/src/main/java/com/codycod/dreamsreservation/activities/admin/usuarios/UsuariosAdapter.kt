package com.codycod.dreamsreservation.activities.admin.usuarios


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UsuariosAdapter(private val list: List<Usuarios>) :
    RecyclerView.Adapter<UsuariosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UsuariosViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UsuariosViewHolder, position: Int) {
        val usuarios = list[position]
        holder.bind(usuarios)
    }
}
