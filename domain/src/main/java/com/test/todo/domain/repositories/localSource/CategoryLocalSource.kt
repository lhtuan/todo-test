package com.test.todo.domain.repositories.localSource

import com.test.todo.domain.models.Category


interface CategoryLocalSource {
    suspend fun getCategories(): List<Category>
    suspend fun addCategories(category: List<Category>)
}