package dev.saurabhmishra.exoplayersample.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = VideoEntity::class,
            parentColumns = ["videoId"],
            childColumns = ["videoId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class CommentEntity(
    @PrimaryKey val commentId: Long,
    val videoId: Long,
    val commentContent: String,
    val likeCount: Int
)
