package dev.saurabhmishra.exoplayersample.di.module

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.saurabhmishra.data.sources.*
import dev.saurabhmishra.exoplayersample.database.ExoplayerSampleDB
import dev.saurabhmishra.exoplayersample.network.Api
import dev.saurabhmishra.exoplayersample.source.*
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
    fun provideVideoLocalSource(db: ExoplayerSampleDB, sharedPreferences: SharedPreferences): VideoLocalSource {
        return VideoLocalSourceImpl(db, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideUserLocalSource(db: ExoplayerSampleDB): UserLocalSource {
        return UserLocalSourceImpl(db)
    }

    @Provides
    @Singleton
    fun provideUserRemoteSource(api: Api): UserRemoteSource {
        return UserRemoteSourceImpl(api, Dispatchers.IO)
    }
}