package dev.saurabhmishra.exoplayersample.database.dao

import androidx.room.Dao
import dev.saurabhmishra.exoplayersample.database.entity.CommentEntity

@Dao
interface CommentDao: BaseDao<CommentEntity> {
}