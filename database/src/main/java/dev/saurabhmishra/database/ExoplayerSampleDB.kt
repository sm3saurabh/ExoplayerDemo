package dev.saurabhmishra.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = DatabaseConstants.VERSION)
abstract class ExoplayerSampleDB : RoomDatabase() {
    companion object {
        fun create(context: Context): RoomDatabase {
            return Room.databaseBuilder(
                context,
                ExoplayerSampleDB::class.java,
                DatabaseConstants.NAME
            ).fallbackToDestructiveMigration().build()
        }
    }
}