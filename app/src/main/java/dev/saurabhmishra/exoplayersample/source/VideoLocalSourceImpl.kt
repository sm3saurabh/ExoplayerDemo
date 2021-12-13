package dev.saurabhmishra.exoplayersample.source

import dev.saurabhmishra.data.sources.VideoLocalSource
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.exoplayersample.database.ExoplayerSampleDB
import dev.saurabhmishra.exoplayersample.database.mappers.toEntity
import dev.saurabhmishra.exoplayersample.database.mappers.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VideoLocalSourceImpl(
    private val db: ExoplayerSampleDB
): VideoLocalSource {
    override suspend fun saveVideos(videoData: List<VideoData>) {
        val entities = videoData.map { data ->
            data.toEntity()
        }
        db.videoDao().insertAll(entities)
    }

    override suspend fun getAllVideos(): List<VideoData> {
        return db.videoDao().getAllVideos().map { entity ->
            entity.toModel()
        }
    }

    override fun getAllVideosFlow(): Flow<List<VideoData>> {
        return db.videoDao().getAllVideosFlow()
            .map { entities ->
                entities.map { entity ->
                    entity.toModel()
                }
            }
    }

    override fun getVideoSuggestions(currentVideoData: VideoData): Flow<List<VideoData>> {
        return db.videoDao().getVideoSuggestions(currentVideoData.videoId)
            .map { entities ->
                entities.map { entity ->
                    entity.toModel()
                }
            }
    }

    override suspend fun deleteLocalVideos() {
        db.videoDao().deleteAllVideos()
    }
}