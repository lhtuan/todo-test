package com.test.todo.data.local

import com.test.todo.data.local.dao.TaskDao
import com.test.todo.data.mapping.toModel
import com.test.todo.domain.models.ResultState
import com.test.todo.domain.models.Task
import com.test.todo.domain.repositories.localSource.TaskLocalSource
import java.text.SimpleDateFormat
import java.util.*

class UserLocalSourceImpl(
    private val taskDao: TaskDao
) : TaskLocalSource {
    companion object {
        val dateFormat = SimpleDateFormat("ddMMyy", Locale.ENGLISH)
    }
    override suspend fun getTasks(date: Calendar): ResultState<List<Task>> {
//        return ResultState.Success(taskDao.getTasksWithCategories(dateFormat.format(date.timeInMillis)).map {
//            it.toModel()
//        })
        return ResultState.Success(emptyList())
    }
}