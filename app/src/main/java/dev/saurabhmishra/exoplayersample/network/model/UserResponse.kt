package dev.saurabhmishra.exoplayersample.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    val userName: String? = null,
    val userId: Long,
    val userPic: String? = null
)
