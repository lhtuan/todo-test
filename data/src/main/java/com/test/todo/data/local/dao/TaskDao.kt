package com.test.todo.data.local.dao

import androidx.room.*
import com.test.todo.data.local.tables.TaskTbl

@Dao
interface TaskDao {
    @Transaction
    @Query("""
        SELECT * FROM task WHERE task.date=:date
    """)
    suspend fun getTasksWithCategories(date: String): List<TaskTbl>
}