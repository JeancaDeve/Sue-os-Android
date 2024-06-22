package com.codycod.dreamsreservation.data.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.codycod.dreamsreservation.data.database.AppDatabase
import com.codycod.dreamsreservation.data.models.MdRoomPartial
import com.codycod.dreamsreservation.data.dao.RoomPartialDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomPartialRepository(application : Application) {
private val roomPartialDao = AppDatabase.getInstance(application).roomPartialDao()

    suspend fun createRoomPartial(roomPartial: MdRoomPartial){
        withContext(Dispatchers.IO){
            roomPartialDao.saveRoom(roomPartial)
        }
    }
    fun getRoomsPartial() : LiveData<List<MdRoomPartial>> = roomPartialDao.getAllRooms()

}