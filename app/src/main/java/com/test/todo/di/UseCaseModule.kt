package com.test.todo.di

import com.test.todo.domain.usecases.DispatcherProvider
import com.test.todo.domain.usecases.GetDateSelectorUseCase
import com.test.todo.domain.usecases.GetTasksUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { DispatcherProvider() }
    factory { GetTasksUseCase(get(), get()) }
    factory { GetDateSelectorUseCase(get()) }
}