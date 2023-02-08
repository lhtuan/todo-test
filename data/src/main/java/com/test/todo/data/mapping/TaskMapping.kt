package com.test.todo.data.mapping

import com.test.todo.data.local.tables.TaskTbl
import com.test.todo.data.local.tables.TaskWithCategories
import com.test.todo.domain.models.Task

fun TaskWithCategories.toModel() = Task(
    id = task.taskId,
    title = task.title,
    date = task.date,
    from = task.from,
    to = task.to,
    description = task.description,
    categories = categories.map { it.toModel() }
)

fun Task.toTable() = TaskTbl(
    taskId = -1L,
    title = title,
    date = date,
    from = from,
    to = to,
    description = description
)