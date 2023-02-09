package com.test.todo.presentation

import androidx.lifecycle.viewModelScope
import com.test.todo.domain.usecases.GenerateCategoriesUseCase
import com.test.todo.domain.usecases.None
import com.test.todo.presentation.base.BaseViewModel

class MainViewModel(
    private val generateCategoriesUseCase: GenerateCategoriesUseCase
) : BaseViewModel() {

    fun initApp() {
        generateCategoriesUseCase(viewModelScope, None()) {}
    }
}