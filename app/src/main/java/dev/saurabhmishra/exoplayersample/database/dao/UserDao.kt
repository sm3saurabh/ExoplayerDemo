package dev.saurabhmishra.exoplayersample.database.dao

import androidx.room.Dao
import dev.saurabhmishra.exoplayersample.database.entity.UserEntity

@Dao
interface UserDao: BaseDao<UserEntity> {

}