package com.codycod.dreamsreservation.activities.admin.usuarios

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R


class UsuariosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuarioadmi)

        val recyclerUsuarios = findViewById<RecyclerView>(R.id.recyclerusuario)

        val listUsuarios = listOf<Usuarios>(
            Usuarios("Nombre:Jesus", "Apellido:Araujo", 4,  9, 53333.00),
            Usuarios("Nombre:Axel", "Apellido:Davalos", 6, 1, 9992.00),
            Usuarios("Nombre:David", "Apellido:Rodriguez",  4,  32, 133444.00),
            Usuarios("Nombre:Kevin", "Apellido:Rizco", 4,  1, 444.00)

        )

        val adapter = UsuariosAdapter(listUsuarios)
        recyclerUsuarios.adapter = adapter
        recyclerUsuarios.layoutManager = LinearLayoutManager(this)
    }
}
