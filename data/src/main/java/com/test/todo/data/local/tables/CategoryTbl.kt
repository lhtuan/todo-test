package com.test.todo.data.local.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryTbl(
    @PrimaryKey val categoryId: Long,
    val name: String
)
