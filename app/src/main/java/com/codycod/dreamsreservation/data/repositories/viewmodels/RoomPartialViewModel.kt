package com.codycod.dreamsreservation.data.repositories.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.codycod.dreamsreservation.data.models.MdRoomPartial
import com.codycod.dreamsreservation.data.repositories.RoomPartialRepository
import kotlinx.coroutines.launch

class RoomPartialViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository = RoomPartialRepository(application)

    fun createRoomPartial(roomPartial: MdRoomPartial) {
        viewModelScope.launch {
            repository.createRoomPartial(roomPartial)
        }
    }

    fun getRoomsPartial() = repository.getRoomsPartial()


}