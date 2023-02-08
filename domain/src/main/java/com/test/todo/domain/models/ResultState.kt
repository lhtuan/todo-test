package com.test.todo.domain.models

sealed class ResultState<out T>(val data: T? = null) {

    /**
     * A state of [data] which shows that we know there is still an update to come.
     */
    data class Loading<T>(private val latestData: T? = null) : ResultState<T>(latestData)

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(private val newData: T?) : ResultState<T>(newData)

    /**
     * A state to show a [failure] is thrown beside the [lastData] which is cached.
     */
    data class Error<T>(val failure: Failure, private val lastData: T? = null) :
        ResultState<T>(lastData)
}

fun <In, Out>ResultState<In?>.map(transform: (In?) -> Out?): ResultState<Out> {
    return when(this) {
        is ResultState.Loading -> ResultState.Loading(transform(data))
        is ResultState.Error -> ResultState.Error(failure = this.failure, transform(data))
        is ResultState.Success -> ResultState.Success(transform(data))
    }
}


