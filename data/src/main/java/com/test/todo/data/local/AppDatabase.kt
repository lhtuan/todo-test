package com.test.todo.data.local

import androidx.room.RoomDatabase
import com.test.todo.data.local.dao.TaskDao
import com.test.todo.data.local.tables.CategoryTbl
//import com.test.todo.data.local.tables.TaskCategoryRef
import com.test.todo.data.local.tables.TaskTbl

@androidx.room.Database(
    entities = [
        TaskTbl::class,
        CategoryTbl::class,
//        TaskCategoryRef::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}