package dev.saurabhmishra.exoplayersample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.saurabhmishra.exoplayersample.database.entity.VideoEntity
import dev.saurabhmishra.exoplayersample.utils.AppConstants

@Database(
    entities = [
        VideoEntity::class,

    ], version = AppConstants.Database.VERSION
)
abstract class ExoplayerSampleDB : RoomDatabase() {
    companion object {
        fun create(context: Context): ExoplayerSampleDB {
            return Room.databaseBuilder(
                context,
                ExoplayerSampleDB::class.java,
                AppConstants.Database.NAME
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}