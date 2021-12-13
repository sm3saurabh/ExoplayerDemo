package dev.saurabhmishra.exoplayersample.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.saurabhmishra.data.sources.CommentsLocalSource
import dev.saurabhmishra.data.sources.CommentsRemoteSource
import dev.saurabhmishra.data.sources.VideoLocalSource
import dev.saurabhmishra.data.sources.VideoRemoteSource
import dev.saurabhmishra.exoplayersample.database.ExoplayerSampleDB
import dev.saurabhmishra.exoplayersample.network.Api
import dev.saurabhmishra.exoplayersample.source.CommentsLocalSourceImpl
import dev.saurabhmishra.exoplayersample.source.CommentsRemoteSourceImpl
import dev.saurabhmishra.exoplayersample.source.VideoLocalSourceImpl
import dev.saurabhmishra.exoplayersample.source.VideoRemoteSourceImpl
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SourceModule {

    @Provides
    @Singleton
    fun provideCommentsLocalSource(db: ExoplayerSampleDB): CommentsLocalSource {
        return CommentsLocalSourceImpl(db)
    }

    @Provides
    @Singleton
    fun provideCommentsRemoteSource(api: Api): CommentsRemoteSource {
        return CommentsRemoteSourceImpl(api, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideVideoRemoteSource(api: Api): VideoRemoteSource {
        return VideoRemoteSourceImpl(api, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideVideoLocalSource(db: ExoplayerSampleDB): VideoLocalSource {
        return VideoLocalSourceImpl(db)
    }
}