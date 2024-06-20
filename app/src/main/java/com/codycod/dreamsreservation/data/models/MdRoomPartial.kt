package com.codycod.dreamsreservation.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codycod.dreamsreservation.data.enums.EnRoomStatus
import com.codycod.dreamsreservation.data.enums.EnTypeRoom

@Entity(tableName = "rooms")
data class MdRoomPartial(
    val price: Float,
    val image: String,
    val content: String,
    @ColumnInfo(name = "type")
    val typeRoom: EnTypeRoom,
){
    @PrimaryKey(autoGenerate = true)
    var code = 0
}
