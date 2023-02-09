package com.test.todo.di

import com.test.todo.presentation.MainViewModel
import com.test.todo.presentation.newTask.NewTaskViewModel
import com.test.todo.presentation.tasks.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { TaskViewModel(get(), get()) }
    viewModel { NewTaskViewModel(get(), get()) }
}