package com.test.todo.data.local

import com.test.todo.data.local.dao.TaskDao
import com.test.todo.data.mapping.toModel
import com.test.todo.data.mapping.toTable
import com.test.todo.domain.Const
import com.test.todo.domain.models.ResultState
import com.test.todo.domain.models.Task
import com.test.todo.domain.repositories.localSource.TaskLocalSource
import java.text.SimpleDateFormat
import java.util.*

class TaskLocalSourceImpl(
    private val taskDao: TaskDao
) : TaskLocalSource {
    companion object {
        val dateFormat = SimpleDateFormat(Const.TASK_DATE_FORMAT, Locale.ENGLISH)
    }

    override suspend fun getTasks(date: Calendar): ResultState<List<Task>> {
        return ResultState.Success(
            taskDao.getTasksWithCategories(dateFormat.format(date.timeInMillis)).map {
                it.toModel()
            })
    }

    override suspend fun addTask(task: Task): ResultState<Void> {
        taskDao.addTaskWithCategories(
            task = task.toTable(),
            categories = task.categories.map { it.toTable() })
        return ResultState.Success(null)
    }
}