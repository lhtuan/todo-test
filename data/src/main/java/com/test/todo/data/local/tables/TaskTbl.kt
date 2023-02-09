package com.test.todo.data.local.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskTbl(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val date: String,
    val from: String,
    val to: String,
    val description: String
)
