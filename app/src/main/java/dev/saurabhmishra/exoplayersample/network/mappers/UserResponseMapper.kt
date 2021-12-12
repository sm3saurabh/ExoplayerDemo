package dev.saurabhmishra.exoplayersample.network.mappers

import dev.saurabhmishra.domain.models.User
import dev.saurabhmishra.exoplayersample.network.model.UserResponse

fun UserResponse.toModel(): User {
    return User(
        userId = this.userId,
        userName = this.userName.orEmpty(),
        userPicUrl = this.userPic.orEmpty()
    )
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        userName, userId, userPicUrl
    )
}