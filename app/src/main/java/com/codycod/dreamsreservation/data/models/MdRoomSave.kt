package com.codycod.dreamsreservation.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codycod.dreamsreservation.data.enums.EnTypeRoom

@Entity(tableName = "rooms")
data class MdRoomSave(
    val price: Float,
    val image: String,
    val content: String,
    @ColumnInfo(name = "type")
    val typeRoom: EnTypeRoom,
    val number : Short
){
    @PrimaryKey(autoGenerate = true)
    var code: Long = 0L
}
