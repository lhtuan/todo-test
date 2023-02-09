package com.test.todo.data.local

import com.test.todo.data.local.dao.CategoryDao
import com.test.todo.data.mapping.toModel
import com.test.todo.data.mapping.toTable
import com.test.todo.domain.models.Category
import com.test.todo.domain.repositories.localSource.CategoryLocalSource

class CategoryLocalSourceImpl(
    private val categoryDao: CategoryDao
): CategoryLocalSource {
    override suspend fun addCategories(category: List<Category>) {
        category.map { it.toTable() }.forEach {
            categoryDao.addCategory(it)
        }
    }

    override suspend fun getCategories(): List<Category> {
        return categoryDao.getCategories().map { it.toModel() }
    }
}