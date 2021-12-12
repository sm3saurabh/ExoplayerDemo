package dev.saurabhmishra.exoplayersample.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.saurabhmishra.data.repository.CommentRepositoryImpl
import dev.saurabhmishra.data.repository.VideoRepositoryImpl
import dev.saurabhmishra.data.sources.CommentsLocalSource
import dev.saurabhmishra.data.sources.CommentsRemoteSource
import dev.saurabhmishra.data.sources.VideoLocalSource
import dev.saurabhmishra.data.sources.VideoRemoteSource
import dev.saurabhmishra.domain.repository.CommentsRepository
import dev.saurabhmishra.domain.repository.VideoRepository

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideVideoRepository(
        localSource: VideoLocalSource,
        remoteSource: VideoRemoteSource
    ): VideoRepository {
        return VideoRepositoryImpl(localSource, remoteSource)
    }

    @Provides
    fun provideCommentsRepository(
        commentsLocalSource: CommentsLocalSource,
        remoteSource: CommentsRemoteSource
    ): CommentsRepository {
        return CommentRepositoryImpl(commentsLocalSource, remoteSource)
    }

}