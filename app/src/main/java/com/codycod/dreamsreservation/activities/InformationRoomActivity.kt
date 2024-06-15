package com.codycod.dreamsreservation.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.functions.Functions
import com.codycod.dreamsreservation.models.room.MdRoom
import com.codycod.dreamsreservation.models.room.loadContentRoom.ContentRoomAdapter
import com.codycod.dreamsreservation.models.room.loadimages.RoomUpdateAdapter


class InformationRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_room_user)

        //verify if exist object_room

        if(intent.hasExtra("object_room")){
            val objectRoom = intent.getSerializableExtra("object_room") as MdRoom

            //get items to insert information
            val rvImagesRoom =
                findViewById<RecyclerView>(R.id.rv_images_room)
            val rvContain = findViewById<RecyclerView>(R.id.rv_contain_room)
            val txtPrice = findViewById<TextView>(R.id.txt_price_info)
            val txtDescription = findViewById<TextView>(R.id.txt_description_info)
            val  txtNFloor = findViewById<TextView>(R.id.txt_n_floor_info)
            val txtNRoom = findViewById<TextView>(R.id.txt_n_room_info)


            txtPrice.text = "S/. ${objectRoom.price}"
            txtDescription.setText(objectRoom.description)
            txtNRoom.text = "Nº ${objectRoom.number}"
            txtNFloor.text = "Nº ${objectRoom.floor}"

            //load info in recycler view

            rvImagesRoom.adapter = RoomUpdateAdapter(Functions.divideText(objectRoom.image))
            rvImagesRoom.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


            rvContain.adapter = ContentRoomAdapter(Functions.divideText(objectRoom.content))


            rvContain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)




        }


        //buttons

        val btnCancel = findViewById<Button>(R.id.btnCancelar)
        val btnIrReservar = findViewById<Button>(R.id.btnIrReservar)

        btnCancel.setOnClickListener {
            finish()
        }

        btnIrReservar.setOnClickListener {
            startActivity(Intent(this, FormReservationActivity::class.java))
        }






    }
}