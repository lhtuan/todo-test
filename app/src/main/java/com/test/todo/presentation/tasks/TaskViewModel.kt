package com.test.todo.presentation.tasks

import com.test.todo.domain.models.ResultState
import com.test.todo.domain.models.Task
import com.test.todo.domain.usecases.GetDateSelectorUseCase
import com.test.todo.domain.usecases.GetTasksUseCase
import com.test.todo.domain.usecases.None
import com.test.todo.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

class TaskViewModel(
    scope: CoroutineScope?,
    private val getTasksUseCase: GetTasksUseCase,
    private val getDateSelectorUseCase: GetDateSelectorUseCase
) : BaseViewModel(scope) {
    private val _tasks = MutableStateFlow<ResultState<List<Task>>?>(
        ResultState.Loading()
    )
    val tasks: StateFlow<ResultState<List<Task>>?> = _tasks

    private val _dates = MutableStateFlow<List<Calendar>?>(
        emptyList()
    )
    val dates: StateFlow<List<Calendar>?> = _dates

    private val _selectedDate = MutableStateFlow<Calendar>(
        Calendar.getInstance()
    )

    init {
        loadSelectedDates()
        loadTasks()
    }

    fun loadTasks() {
        getTasksUseCase(getScope(), GetTasksUseCase.In(date = _selectedDate.value)) {
            _tasks.value = it
        }
    }

    fun setSelectedDate(date: Calendar) {
        _selectedDate.value = date
        loadTasks()
    }

    private fun loadSelectedDates() {
        getDateSelectorUseCase(getScope(), None()) {
            _dates.value = it
        }
    }
}