package dev.saurabhmishra.exoplayersample.database.mappers

import dev.saurabhmishra.domain.models.User
import dev.saurabhmishra.exoplayersample.database.entity.UserEntity

fun User.toEntity(): UserEntity {
    return UserEntity(
        userId, userName, userPicUrl
    )
}

fun UserEntity.toModel(): User {
    return User(
        userId, userName, userPicUrl
    )
}