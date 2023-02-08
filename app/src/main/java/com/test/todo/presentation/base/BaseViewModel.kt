package com.test.todo.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

open class BaseViewModel(private val scope: CoroutineScope? = null) : ViewModel() {
    protected fun getScope() = scope ?: viewModelScope
}