package com.codycod.dreamsreservation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.data.database.AppDatabase
import com.codycod.dreamsreservation.data.enums.EnTypeRoom
import com.codycod.dreamsreservation.utils.functions.Functions
import com.codycod.dreamsreservation.data.models.MdRoom
import com.codycod.dreamsreservation.data.models.MdRoomPartial
import com.codycod.dreamsreservation.data.repositories.dao.RoomPartialDao
import com.codycod.dreamsreservation.data.repositories.viewmodels.RoomPartialViewModel
import com.codycod.dreamsreservation.ui.adapters.ContentRoomAdapter
import com.codycod.dreamsreservation.ui.adapters.ImagesRoomAdapter
import com.codycod.dreamsreservation.ui.adapters.ReviewsRoomAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class InformationRoomActivity : AppCompatActivity() {

    //declaration of dao and database with late init

    private lateinit var getObjectRoom: MdRoom
    private lateinit var roomViewModel: RoomPartialViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_room)


        //init of viewmodel

        roomViewModel = ViewModelProvider(this)[RoomPartialViewModel::class.java]

        //verify if exist object_room

        if (intent.hasExtra("object_room")) {
            val objectRoom = intent.getSerializableExtra("object_room") as MdRoom
            getObjectRoom = objectRoom

            //get items to insert information
            val rvImagesRoom =
                findViewById<RecyclerView>(R.id.rv_images_room)
            val rvContain = findViewById<RecyclerView>(R.id.rv_contain_room)
            val txtPrice = findViewById<TextView>(R.id.txt_price_info)
            val txtDescription = findViewById<TextView>(R.id.txt_description_info)
            val txtNFloor = findViewById<TextView>(R.id.txt_n_floor_info)
            val txtNRoom = findViewById<TextView>(R.id.txt_n_room_info)

            //get recycler view of reviews
            val rvReviews = findViewById<RecyclerView>(R.id.rv_reviews)

            rvReviews.adapter = ReviewsRoomAdapter(objectRoom.reviews)
            rvReviews.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


            txtPrice.text = "S/. ${objectRoom.price}"
            txtDescription.setText(objectRoom.description)
            txtNRoom.text = "Nº ${objectRoom.number}"
            txtNFloor.text = "Nº ${objectRoom.floor}"

            //load info in recycler view

            rvImagesRoom.adapter = ImagesRoomAdapter(Functions.divideText(objectRoom.image))
            rvImagesRoom.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


            rvContain.adapter = ContentRoomAdapter(Functions.divideText(objectRoom.content))


            rvContain.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        }


        //floating button and action
        val btnMoreOptions = findViewById<FloatingActionButton>(R.id.f_btn_more_options_room)

        btnMoreOptions.setOnClickListener {
            showPopupMenu(it)
        }


    }

    //to show options of the menu and click option
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu_room_info, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.item_pm_reserve -> {
                    startActivity(Intent(this, FormReservationActivity::class.java))
                    true
                }

                R.id.item_pm_save -> {
                    saveRoomPartial(getObjectRoom)
                    true
                }

                else -> false

            }
        }

        popupMenu.show()

    }


    //to save a entity room partial in database

    private fun saveRoomPartial(roomEntity: MdRoom) {
        val saveRoom = MdRoomPartial(
            image = "df",
            price = 325f,
            content = "frr",
            typeRoom = EnTypeRoom.SUITES
        )
        try {
            roomViewModel.createRoomPartial(saveRoom)
        }catch (e : Exception){
          Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

}