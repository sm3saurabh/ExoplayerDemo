package dev.saurabhmishra.exoplayersample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.saurabhmishra.exoplayersample.database.dao.CommentDao
import dev.saurabhmishra.exoplayersample.database.dao.UserDao
import dev.saurabhmishra.exoplayersample.database.dao.VideoDao
import dev.saurabhmishra.exoplayersample.database.entity.CommentEntity
import dev.saurabhmishra.exoplayersample.database.entity.UserEntity
import dev.saurabhmishra.exoplayersample.database.entity.VideoEntity
import dev.saurabhmishra.exoplayersample.utils.AppConstants

@Database(
    entities = [
        VideoEntity::class,
        UserEntity::class,
        CommentEntity::class
    ], version = AppConstants.Database.VERSION
)
abstract class ExoplayerSampleDB : RoomDatabase() {

    abstract fun videoDao(): VideoDao
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao

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