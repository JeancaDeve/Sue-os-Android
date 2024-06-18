package com.codycod.dreamsreservation.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codycod.dreamsreservation.enums.EnRoomStatus
import com.codycod.dreamsreservation.enums.EnTypeRoom

@Entity(tableName = "room")
data class MdRoomPartial(
    val description: Int, //change to string later
    val price: Float,
    val image: String,
    val content: String,
    @ColumnInfo(name = "type")
    val typeRoom: EnTypeRoom,
){
    @PrimaryKey(autoGenerate = true)
    val code = 0
}
