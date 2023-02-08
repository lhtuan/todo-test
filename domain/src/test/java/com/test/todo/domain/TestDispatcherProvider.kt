package com.test.todo.domain

import com.test.todo.domain.usecases.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher

class TestDispatcherProvider(testDispatcher: CoroutineDispatcher) : DispatcherProvider() {
    override val io: CoroutineDispatcher = testDispatcher
}