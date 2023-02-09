package com.test.todo.domain.usecases

import com.test.todo.domain.models.Category
import com.test.todo.domain.repositories.CategoryRepo

class GetCategoriesUseCase(
    dispatcherProvider: DispatcherProvider,
    private val categoryRepo: CategoryRepo
): BaseUseCase<List<Category>, None>(dispatcherProvider) {
    override suspend fun run(params: None): List<Category> {
        return categoryRepo.getCategories()
    }
}