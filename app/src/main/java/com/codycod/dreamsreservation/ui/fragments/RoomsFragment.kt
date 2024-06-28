package com.codycod.dreamsreservation.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.ui.activities.RoomsFilterActivity
import com.codycod.dreamsreservation.data.enums.EnTypeRoom
import com.codycod.dreamsreservation.data.models.MdButtonTypeRoom
import com.codycod.dreamsreservation.data.viewmodels.RoomsViewModel
import com.codycod.dreamsreservation.data.viewmodels.UserViewModel
import com.codycod.dreamsreservation.ui.adapters.RoomsListAdapter

class RoomsFragment : Fragment() {

    private lateinit var roomViewModel: RoomsViewModel
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rooms_user, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get recyclerView
        val rvMatrimonial = view.findViewById<RecyclerView>(R.id.rv_habitaciones_matri)

        val roomsListAdapter = RoomsListAdapter()

        roomViewModel = ViewModelProvider(this)[RoomsViewModel::class.java]
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        //set properties in recycler view
        rvMatrimonial.layoutManager = LinearLayoutManager(
            view.context, LinearLayoutManager.HORIZONTAL, false
        )
        rvMatrimonial.adapter = roomsListAdapter


        //set rooms of firebase

        roomViewModel.listRooms.observe(viewLifecycleOwner, Observer { rooms ->
            roomsListAdapter.setRooms(rooms)
        })


        //load rooms
        roomViewModel.getAvailableRoomsByType(lifecycleOwner = this, userViewModel = userViewModel)

        //get container buttons of type room
        val containerButtons = view.findViewById<LinearLayout>(R.id.container_buttons_type_room)

        val listButtons = listOf(
            MdButtonTypeRoom("TODO", null, true),
            MdButtonTypeRoom("SUITES", EnTypeRoom.SUITES, false),
            MdButtonTypeRoom("MATRIMONIAL", EnTypeRoom.MATRIMONIAL, false),
            MdButtonTypeRoom("INDIVIDUAL", EnTypeRoom.INDIVIDUAL, false)
        )

        //to verify the type request
        listButtons.forEach { buttonInfo ->
            val button =
                Button(ContextThemeWrapper(view.context, R.style.ButtonTypeRoomStyle)).apply {
                    text = buttonInfo.text
                    setOnClickListener {
                        when (buttonInfo.typeRoom) {
                            buttonInfo.typeRoom -> {
                                roomViewModel.getAvailableRoomsByType(
                                    buttonInfo.typeRoom,
                                    userViewModel,
                                    this@RoomsFragment
                                )
                                buttonInfo.isActive = !buttonInfo.isActive
                            }

                            else -> {}
                        }
                    }
                }
            containerButtons.addView(button)
        }
        //set action search
        val searchBtn = view.findViewById<LinearLayout>(R.id.btn_input_search)

        searchBtn.setOnClickListener {
            startActivity(Intent(view.context, RoomsFilterActivity::class.java))
        }
    }

    //instance of RoomFragment
    companion object {
        fun newInstance(): RoomsFragment = RoomsFragment()
    }


}