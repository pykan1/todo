package com.example.todo.di

import android.content.Context
import androidx.room.Room
import com.example.todo.data.local.AppDatabase
import com.example.todo.data.local.repository.DayRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ToDoDatabaseModule {

    @Provides
    fun provideAppToDO(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "day"
        ).build()
    }

    @Provides
    fun provideToDoDao(appDatabase: AppDatabase): DayRepository {
        return appDatabase.dayDao()
    }
}

