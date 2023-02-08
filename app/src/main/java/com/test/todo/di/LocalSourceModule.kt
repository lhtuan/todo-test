package com.test.todo.di

import androidx.room.Room
import com.test.todo.data.local.AppDatabase
import com.test.todo.data.local.TaskLocalSourceImpl
import com.test.todo.domain.repositories.localSource.TaskLocalSource
import org.koin.dsl.module

val localSourceModule = module {
    //Database
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "todo.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().taskDao() }

    //Data source
    single<TaskLocalSource> { TaskLocalSourceImpl(get()) }
}