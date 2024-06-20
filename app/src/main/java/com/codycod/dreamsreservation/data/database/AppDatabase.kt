package com.codycod.dreamsreservation.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codycod.dreamsreservation.data.models.MdRoomPartial
import com.codycod.dreamsreservation.data.repositories.dao.RoomPartialDao

@Database(entities = [MdRoomPartial::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roomPartialDao(): RoomPartialDao

    companion object {
        private const val DATABASE_NAME = "dreams_database"

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