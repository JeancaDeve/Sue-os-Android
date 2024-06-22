package com.codycod.dreamsreservation.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.codycod.dreamsreservation.data.models.MdRoomPartial

@Dao
interface RoomPartialDao {
    @Query("SELECT * FROM rooms")
    fun getAllRooms() : LiveData<List<MdRoomPartial>>

    @Insert
    fun saveRoom(room : MdRoomPartial)

    @Delete
    fun deleteRoom(room : MdRoomPartial)
    


}