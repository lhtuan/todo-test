package com.test.todo.domain.repositories.localSource

import com.test.todo.domain.models.ResultState
import com.test.todo.domain.models.Task
import java.util.Calendar

interface TaskLocalSource {
    suspend fun getTasks(date: Calendar): ResultState<List<Task>>
}