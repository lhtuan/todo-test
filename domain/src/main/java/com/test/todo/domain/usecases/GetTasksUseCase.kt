package com.test.todo.domain.usecases

import com.test.todo.domain.models.ResultState
import com.test.todo.domain.models.Task
import com.test.todo.domain.repositories.TaskRepo
import java.util.Calendar

class GetTasksUseCase(
    dispatcherProvider: DispatcherProvider,
    private val taskRepo: TaskRepo
): BaseUseCase<ResultState<List<Task>>, GetTasksUseCase.In>(dispatcherProvider) {
    override suspend fun run(params: In): ResultState<List<Task>> {
        return taskRepo.getTasks(params.date)
    }

    data class In(
        val date: Calendar
    )
}