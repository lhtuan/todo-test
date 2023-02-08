package com.test.todo.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Help to when write UT
 */
open class DispatcherProvider {
    /**
     * Override with variable when write test case to provide test dispatcher
     */
    open val io: CoroutineDispatcher by lazy { Dispatchers.IO }
}