package dev.saurabhmishra.exoplayersample.network

import dev.saurabhmishra.exoplayersample.network.model.CommentResponse
import dev.saurabhmishra.exoplayersample.network.model.UserResponse
import dev.saurabhmishra.exoplayersample.network.model.VideoResponse

interface Api {

    suspend fun loadVideos(): List<VideoResponse>

    suspend fun loadComments(): List<CommentResponse>

    suspend fun loadUsers(): List<UserResponse>

}