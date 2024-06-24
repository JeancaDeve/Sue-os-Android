package com.codycod.dreamsreservation.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.codycod.dreamsreservation.data.database.AppDatabase

import com.codycod.dreamsreservation.data.models.MdRoomSave
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomSaveRepository(application: Application) {
    private val roomPartialDao = AppDatabase.getInstance(application).roomSaveDao()

    suspend fun createRoomSave(roomPartial: MdRoomSave) {
        withContext(Dispatchers.IO) {
            roomPartialDao.saveRoom(roomPartial)
        }
    }

    fun getAllRoomsSave(): LiveData<List<MdRoomSave>> = roomPartialDao.getAllRooms()

    fun getRoomSaveByNumber(number: Short): MdRoomSave? {
        return roomPartialDao.getRoomByNumber(number)
    }



}