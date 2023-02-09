package com.test.todo.di

import com.test.todo.domain.usecases.*
import org.koin.dsl.module

val useCaseModule = module {
    single { DispatcherProvider() }
    factory { GetTasksUseCase(get(), get()) }
    factory { GetDateSelectorUseCase(get()) }
    factory { GenerateCategoriesUseCase(get(), get()) }
    factory { GetCategoriesUseCase(get(), get()) }
    factory { CreateTaskUseCase(get(), get(), get()) }
}