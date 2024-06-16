package com.codycod.dreamsreservation.activities

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.activities.adapters.RoomsFilterAdapter
import com.codycod.dreamsreservation.functions.Functions
import com.codycod.dreamsreservation.models.MdRoom

class RoomsFilterActivity : AppCompatActivity() {

    private lateinit var listRoomsFilter: List<MdRoom>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms_filter)

        listRoomsFilter = Functions.listRoomsByContent()

        val recyclerView = findViewById<RecyclerView>(R.id.rv_rooms_filter)
        val inputFilter = findViewById<EditText>(R.id.input_filter)

        //set focus in input filter and to open keyboard

        inputFilter.requestFocus()
        val inputMm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMm.showSoftInput(inputFilter, InputMethodManager.SHOW_IMPLICIT)

        recyclerView.adapter = RoomsFilterAdapter(listRoomsFilter)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        inputFilter.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                listRoomsFilter = Functions.listRoomsByContent(inputFilter.text.toString())
                recyclerView.adapter = RoomsFilterAdapter(listRoomsFilter)
                return@setOnEditorActionListener true
            }
            false
        }
    }

}