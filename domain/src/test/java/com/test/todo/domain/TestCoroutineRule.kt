package com.test.todo.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


@ExperimentalCoroutinesApi
class TestCoroutineRule : TestRule {
    val testCoroutineDispatcher = UnconfinedTestDispatcher()
    val testCoroutineScope = TestScope(testCoroutineDispatcher)

    override fun apply(base: Statement, description: Description): Statement =
        object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                Dispatchers.setMain(testCoroutineDispatcher)
                base.evaluate()

                Dispatchers.resetMain()
            }
        }

    fun runBlockingTest(block: suspend TestScope.() -> Unit) =
        testCoroutineScope.runTest { block() }
}