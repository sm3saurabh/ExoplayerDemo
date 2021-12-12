package dev.saurabhmishra.exoplayersample.database.dao

import androidx.room.Dao
import dev.saurabhmishra.exoplayersample.database.entity.VideoEntity

@Dao
interface VideoDao: BaseDao<VideoEntity> {

}