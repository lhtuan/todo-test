package com.test.todo.data.local.tables

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(
    tableName = "task_category_ref", primaryKeys = ["tId", "cId"]
)
data class TaskCategoryRef(
    val tId: Long,
    val cId: Long
)

data class TaskWithCategories(
    @Embedded val task: TaskTbl,
    @Relation(
        parentColumn = "id",
        entity = CategoryTbl::class,
        entityColumn = "id",
        associateBy = Junction(
            TaskCategoryRef::class,
            parentColumn = "tId",
            entityColumn = "cId"
        )
    )
    val categories: List<CategoryTbl>
)