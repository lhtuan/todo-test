package com.test.todo.domain.usecases

import java.util.Calendar

class GetDateSelectorUseCase(
    dispatcherProvider: DispatcherProvider,
): BaseUseCase<List<Calendar>, None>(dispatcherProvider) {
    override suspend fun run(params: None): List<Calendar> {
        //Get today and next 14 days
        return IntRange(0, 15).map {
            Calendar.getInstance().apply {
                add(Calendar.DATE, it)
            }
        }
    }
}