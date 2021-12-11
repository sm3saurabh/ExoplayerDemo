package dev.saurabhmishra.exoplayersample.di.module

import android.content.Context
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.saurabhmishra.database.ExoplayerSampleDB
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideExoplayerSampleDB(
        @ApplicationContext context: Context
    ): RoomDatabase {
        return ExoplayerSampleDB.create(context)
    }
}