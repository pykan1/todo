package com.example.todo.di

import android.content.Context
import androidx.room.Room
import com.example.todo.data.local.AppDatabase
import com.example.todo.data.local.repository.ToDoRepository
import com.example.todo.data.local.repositoryImpl.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object ToDoDatabaseModule {

    @Provides
    fun provideAppDateCard(@ApplicationContext appContext: Context): AppDatabase {
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