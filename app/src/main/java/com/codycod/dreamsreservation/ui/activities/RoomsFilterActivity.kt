package com.codycod.dreamsreservation.ui.activities

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codycod.dreamsreservation.R
import com.codycod.dreamsreservation.ui.adapters.RoomsFilterAdapter
import com.codycod.dreamsreservation.data.models.MdRoom
import com.codycod.dreamsreservation.data.viewmodels.RoomsViewModel
import com.codycod.dreamsreservation.data.viewmodels.UserViewModel

class RoomsFilterActivity : AppCompatActivity() {

    private lateinit var listRoomsFilter: List<MdRoom>
    private lateinit var viewModel: RoomsViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms_filter)
        //init of view model
        viewModel = ViewModelProvider(this)[RoomsViewModel::class.java]
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val recyclerView = findViewById<RecyclerView>(R.id.rv_rooms_filter)
        val inputFilter = findViewById<EditText>(R.id.input_filter)

        //set focus in input filter and to open keyboard
        inputFilter.requestFocus()
        val inputMm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMm.showSoftInput(inputFilter, InputMethodManager.SHOW_IMPLICIT)

        //get data of firebase

        viewModel.getRoomByContent(userViewModel = userViewModel, lifecycleOwner = this)


        val adapter = RoomsFilterAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        //load data

        viewModel.listRooms.observe(this, Observer { rooms ->
            rooms?.let {
                adapter.setRoomsFilter(it)
            }
        })

        inputFilter.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getRoomByContent(inputFilter.text.toString(), userViewModel, this)

                return@setOnEditorActionListener true
            }
            false
        }
    }
}