package com.test.todo.domain.repositories

import com.test.todo.domain.models.ResultState
import com.test.todo.domain.models.Task
import com.test.todo.domain.repositories.localSource.TaskLocalSource
import java.util.*

class TaskRepo(
    private val taskLocalSource: TaskLocalSource
) {
    suspend fun getTasks(date: Calendar): ResultState<List<Task>> {
        return taskLocalSource.getTasks(date)
    }

    suspend fun addTask(task: Task) = taskLocalSource.addTask(task)
}