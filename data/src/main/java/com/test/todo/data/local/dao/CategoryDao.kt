package com.test.todo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.test.todo.data.local.tables.CategoryTbl

@Dao
interface CategoryDao {
    @Insert
    fun addCategory(categoryTbl: CategoryTbl): Long

    @Query("SELECT * from category")
    fun getCategories(): List<CategoryTbl>
}