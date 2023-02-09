package com.test.todo.domain.usecases

import com.test.todo.domain.models.Category
import com.test.todo.domain.repositories.CategoryRepo

class GenerateCategoriesUseCase(
    dispatcherProvider: DispatcherProvider,
    private val categoryRepo: CategoryRepo
): BaseUseCase<Unit, None>(dispatcherProvider) {
    override suspend fun run(params: None) {
        if(categoryRepo.getCategories().isEmpty()) {
            categoryRepo.addCategories(listOf(
                Category(0, "Marketing"),
                Category(0, "Dev"),
                Category(0, "Mobile"),
                Category(0, "UI Design"),
                Category(0, "HTML"),
                Category(0, "Graphic Design"),
                Category(0, "Mobile App"),
                Category(0, "iOS App"),
                Category(0, "Magento"),
                Category(0, "SEO")
            ))
        }
    }
}