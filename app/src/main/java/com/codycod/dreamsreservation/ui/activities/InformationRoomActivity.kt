package com.codycod.dreamsreservation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.utils.Functions
import com.codycod.dreamsreservation.data.models.MdRoom
import com.codycod.dreamsreservation.data.models.MdRoomSave
import com.codycod.dreamsreservation.data.viewmodels.RoomSaveViewModel
import com.codycod.dreamsreservation.ui.adapters.ContentRoomAdapter
import com.codycod.dreamsreservation.ui.adapters.ImagesRoomAdapter
import com.codycod.dreamsreservation.ui.adapters.ReviewsRoomAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class InformationRoomActivity : AppCompatActivity() {

    //declaration of dao and database with late init

    private lateinit var getObjectRoom: MdRoom
    private lateinit var roomViewModel: RoomSaveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_room)


        //init of viewmodel

        roomViewModel = ViewModelProvider(this)[RoomSaveViewModel::class.java]

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
            rvImagesRoom.adapter = ImagesRoomAdapter(objectRoom.image)
            rvImagesRoom.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            rvContain.adapter = ContentRoomAdapter(objectRoom.content)

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
                    val intent = Intent(this, FormReservationActivity::class.java)
                    intent.putExtra("room_reservation", getObjectRoom)
                    startActivity(intent)
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
        val saveRoom = MdRoomSave(
            number = roomEntity.number,
            image = roomEntity.image[0],
            price = roomEntity.price,
            content = Functions.arrayToString(roomEntity.content),
            typeRoom = roomEntity.typeRoom
        )
        try {
            roomViewModel.createRoomPartial(saveRoom)
            Toast.makeText(this, "Habitación Guardada", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    //to verify if exist the room in the database

    private fun roomExist(roomEntity: MdRoom): Boolean {
        // return roomViewModel.getRoomByNumber(roomEntity.number) != null
        return false

    }

}