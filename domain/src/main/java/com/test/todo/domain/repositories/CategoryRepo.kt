package com.test.todo.domain.repositories

import com.test.todo.domain.models.Category
import com.test.todo.domain.repositories.localSource.CategoryLocalSource

class CategoryRepo(
    private val categoryLocalSource: CategoryLocalSource
) {

    suspend fun addCategories(categories: List<Category>) =
        categoryLocalSource.addCategories(categories)

    suspend fun getCategories() = categoryLocalSource.getCategories()
}