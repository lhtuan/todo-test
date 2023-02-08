package com.test.todo.data.local.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskTbl(
    @PrimaryKey val taskId: Long,
    val title: String,
    val date: String,
    val from: String,
    val to: String,
    val description: String
)
