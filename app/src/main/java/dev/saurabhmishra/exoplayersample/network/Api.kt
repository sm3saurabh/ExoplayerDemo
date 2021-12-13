package dev.saurabhmishra.exoplayersample.network

import dev.saurabhmishra.exoplayersample.network.model.CommentResponse
import dev.saurabhmishra.exoplayersample.network.model.UserResponse
import dev.saurabhmishra.exoplayersample.network.model.VideoResponse
import retrofit2.http.GET

interface Api {

    @GET("/v3/0304b23e-f348-4d20-8706-798b116ad17b")
    suspend fun loadVideos(): List<VideoResponse>


    @GET("/v3/85a3ef4d-ff29-4c71-9b98-b0851d43331b")
    suspend fun loadComments(): List<CommentResponse>

    @GET("/v3/72556eb4-0ba8-438e-8a08-9b25ca48905e")
    suspend fun loadUsers(): List<UserResponse>

}