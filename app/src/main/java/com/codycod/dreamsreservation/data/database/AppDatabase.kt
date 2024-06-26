package com.codycod.dreamsreservation.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codycod.dreamsreservation.data.models.MdRoomSave
import com.codycod.dreamsreservation.data.dao.RoomSaveDao

@Database(entities = [MdRoomSave::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomSaveDao(): RoomSaveDao

    companion object {
        private const val DATABASE_NAME = "dreams_db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }


    }
}