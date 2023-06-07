package com.example.todo.di

import android.content.Context
import androidx.room.Room
import com.example.todo.data.local.AppDatabase
import com.example.todo.data.local.AppDatabase2
import com.example.todo.data.local.repository.DayRepository
import com.example.todo.data.local.repository.ToDoRepository
import com.example.todo.data.local.repositoryImpl.ToDoRepositoryImpl
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
            "todo"
        ).build()
    }

    @Provides
    fun provideToDoDao(appDatabase: AppDatabase): ToDoRepository {
        return appDatabase.toDoDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DayDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDay(@ApplicationContext appContext: Context): AppDatabase2 {
        return Room.databaseBuilder(
            appContext,
            AppDatabase2::class.java,
            "day"
        ).build()
    }

    @Provides
    fun provideUserRepository(appDatabase: AppDatabase2): DayRepository {
        return appDatabase.dayDao()
    }

}