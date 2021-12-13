package dev.saurabhmishra.exoplayersample.source

import android.content.SharedPreferences
import dev.saurabhmishra.data.sources.VideoLocalSource
import dev.saurabhmishra.domain.models.VideoData
import dev.saurabhmishra.exoplayersample.database.ExoplayerSampleDB
import dev.saurabhmishra.exoplayersample.database.mappers.toEntity
import dev.saurabhmishra.exoplayersample.database.mappers.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class VideoLocalSourceImpl(
    private val db: ExoplayerSampleDB,
    private val sharedPreferences: SharedPreferences,
    private val executionContext: CoroutineContext
): VideoLocalSource {
    override suspend fun saveVideos(videoData: List<VideoData>) {
        withContext(executionContext) {
            val entities = videoData.map { data ->
                data.toEntity()
            }
            db.videoDao().insertAll(entities)
        }
    }

    override suspend fun getAllVideos(): List<VideoData> {
        return withContext(executionContext) {
            db.videoDao().getAllVideos().map { entity ->
                entity.toModel()
            }
        }
    }

    override fun getAllVideosFlow(): Flow<List<VideoData>> {
        return db.videoDao().getAllVideosFlow()
            .map { entities ->
                entities.map { entity ->
                    entity.toModel()
                }
            }.flowOn(executionContext)
    }

    override fun getVideoSuggestions(currentVideoData: VideoData): Flow<List<VideoData>> {
        return db.videoDao().getVideoSuggestions(currentVideoData.videoId)
            .map { entities ->
                entities.map { entity ->
                    entity.toModel()
                }
            }.flowOn(executionContext)
    }

    override suspend fun deleteLocalVideos() {
        withContext(executionContext) {
            db.videoDao().deleteAllVideos()
        }
    }

    override suspend fun getCurrentSelectedVideo(): VideoData? {
        return withContext(executionContext) {
            val currentSelectedVideoId = sharedPreferences.getLong(CURRENT_SELECTED_VIDEO_ID, -1L)

            if (currentSelectedVideoId < 0) {
                null
            } else {
                db.videoDao().getVideoForId(currentSelectedVideoId)?.toModel()
            }
        }
    }

    override suspend fun setCurrentSelectedVideo(videoData: VideoData) {
        withContext(executionContext) {
            sharedPreferences.edit().putLong(CURRENT_SELECTED_VIDEO_ID, videoData.videoId).commit()
        }
    }

    companion object {
        private const val CURRENT_SELECTED_VIDEO_ID = "CURRENT_SELECTED_VIDEO_ID"
    }
}