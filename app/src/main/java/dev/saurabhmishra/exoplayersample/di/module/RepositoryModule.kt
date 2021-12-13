package dev.saurabhmishra.exoplayersample.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.saurabhmishra.data.repository.CommentRepositoryImpl
import dev.saurabhmishra.data.repository.UserRepositoryImpl
import dev.saurabhmishra.data.repository.VideoRepositoryImpl
import dev.saurabhmishra.data.sources.*
import dev.saurabhmishra.domain.repository.CommentsRepository
import dev.saurabhmishra.domain.repository.UserRepository
import dev.saurabhmishra.domain.repository.VideoRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideVideoRepository(
        localSource: VideoLocalSource,
        remoteSource: VideoRemoteSource
    ): VideoRepository {
        return VideoRepositoryImpl(localSource, remoteSource)
    }

    @Provides
    @Singleton
    fun provideCommentsRepository(
        commentsLocalSource: CommentsLocalSource,
        remoteSource: CommentsRemoteSource
    ): CommentsRepository {
        return CommentRepositoryImpl(commentsLocalSource, remoteSource)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userLocalSource: UserLocalSource,
        userRemoteSource: UserRemoteSource
    ): UserRepository {
        return UserRepositoryImpl(userLocalSource, userRemoteSource)
    }

}