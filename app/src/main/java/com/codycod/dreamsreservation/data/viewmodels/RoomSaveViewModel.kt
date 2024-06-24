package com.codycod.dreamsreservation.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.codycod.dreamsreservation.data.models.MdRoomSave
import com.codycod.dreamsreservation.data.repositories.RoomSaveRepository
import kotlinx.coroutines.launch

class RoomSaveViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository = RoomSaveRepository(application)

    fun createRoomPartial(roomPartial: MdRoomSave) {
        viewModelScope.launch {
            repository.createRoomSave(roomPartial)
        }
    }

    fun getRoomsPartial() = repository.getAllRoomsSave()

    fun getRoomByNumber(number: Short) {
       viewModelScope.launch {
            repository.getRoomSaveByNumber(number)
        }
    }


}