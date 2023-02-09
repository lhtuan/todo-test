package com.test.todo.di

import com.test.todo.domain.repositories.CategoryRepo
import com.test.todo.domain.repositories.TaskRepo
import org.koin.dsl.module

val repositoryModule = module {
    single { TaskRepo(get()) }
    single { CategoryRepo(get()) }
}