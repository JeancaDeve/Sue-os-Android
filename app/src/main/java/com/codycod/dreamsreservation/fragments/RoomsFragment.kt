package com.codycod.dreamsreservation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.activities.roomsfilter.RoomsFilterActivity
import com.codycod.dreamsreservation.enums.EnTypeRoom
import com.codycod.dreamsreservation.functions.Functions
import com.codycod.dreamsreservation.models.ButtonTypeRoom
import com.codycod.dreamsreservation.models.room.RoomsListAdapter

class RoomsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rooms_user, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get recyclerViews
        val rvMatrimonial = view.findViewById<RecyclerView>(R.id.rv_habitaciones_matri)




        rvMatrimonial.adapter = RoomsListAdapter(Functions.listRoomsByContent())
        rvMatrimonial.layoutManager = LinearLayoutManager(
            view.context, LinearLayoutManager.HORIZONTAL, false
        )


        //get container buttons of type room

        val containerButtons = view.findViewById<LinearLayout>(R.id.container_buttons_type_room)

        val listButtons = listOf(
            ButtonTypeRoom("TODO", null, true),
            ButtonTypeRoom("SUITES", EnTypeRoom.SUITES, false),
            ButtonTypeRoom("MATRIMONIAL", EnTypeRoom.MATRIMONIAL, false),
            ButtonTypeRoom("INDIVIDUAL", EnTypeRoom.INDIVIDUAL, false)
        )

        listButtons.forEach { buttonInfo ->
            val button =
                Button(ContextThemeWrapper(view.context, R.style.ButtonTypeRoomStyle)).apply {
                    text = buttonInfo.text
                    setOnClickListener {
                        when (buttonInfo.typeRoom) {
                            buttonInfo.typeRoom -> {
                                rvMatrimonial.adapter =
                                    RoomsListAdapter(Functions.listRoomsByType(buttonInfo.typeRoom))
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

    companion object {
        fun newInstance(): RoomsFragment = RoomsFragment()
    }

    //add event onclick listener


}