package com.test.todo.data.local.dao

import androidx.room.*
import com.test.todo.data.local.tables.CategoryTbl
import com.test.todo.data.local.tables.TaskCategoryRef
import com.test.todo.data.local.tables.TaskTbl
import com.test.todo.data.local.tables.TaskWithCategories

@Dao
interface TaskDao {
    @Transaction
    @Query("""
        SELECT * FROM task WHERE task.date=:date
    """)
    suspend fun getTasksWithCategories(date: String): List<TaskWithCategories>

    @Insert
    suspend fun addTask(task: TaskTbl): Long

    @Insert
    suspend fun addTaskCategoryRef(ref: TaskCategoryRef)

    @Transaction
    suspend fun addTaskWithCategories(task: TaskTbl, categories: List<CategoryTbl>) {
        val taskId = addTask(task)
        for (category in categories) {
            addTaskCategoryRef(TaskCategoryRef(tId = taskId, cId = category.id))
        }
    }
}