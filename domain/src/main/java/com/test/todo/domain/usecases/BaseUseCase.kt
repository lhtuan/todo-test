package com.test.todo.domain.usecases

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCase<Out, In>(
    private val dispatcherProvider: DispatcherProvider
) {
    abstract suspend fun run(params: In): Out

    /**
     * For multiple use cases run
     *
     * viewModelScope.launch {
     *   value1 = useCase1(this, None())
     *   value2 = useCase2(this, None())
     * }
     *
     */
    open suspend operator fun invoke(scope: CoroutineScope, params: In): Out {
        return withContext(scope.coroutineContext + dispatcherProvider.io) {
            run(params)
        }
    }

    /**
     * For single use case run
     * useCase1(viewModelScope, None()) {
     *  value1 = it
     * }
     *  * useCase2(viewModelScope, None()) {
     *   value2 = it
     * }
     */
    open operator fun invoke(scope: CoroutineScope, params: In, callback: (Out) -> Unit = {}) {
        scope.launch {
            val result = withContext(scope.coroutineContext + dispatcherProvider.io) {
                run(params)
            }
            callback.invoke(result)
        }
    }
}