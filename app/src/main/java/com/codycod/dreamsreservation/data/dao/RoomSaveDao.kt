package com.codycod.dreamsreservation.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.codycod.dreamsreservation.data.models.MdRoomSave

@Dao
interface RoomSaveDao {
    @Query("SELECT * FROM rooms")
    fun getAllRooms() : LiveData<List<MdRoomSave>>

    @Insert
    fun saveRoom(room : MdRoomSave)

    @Delete
    fun deleteRoom(room : MdRoomSave)

    @Query("SELECT * FROM rooms WHERE number = :numberRoom")
    fun getRoomByNumber(numberRoom : Short) : MdRoomSave?



}