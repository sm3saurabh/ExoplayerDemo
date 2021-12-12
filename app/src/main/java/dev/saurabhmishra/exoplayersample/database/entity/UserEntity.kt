package dev.saurabhmishra.exoplayersample.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val userId: Long,
    val userName: String,
    val userPicUrl: String
)
