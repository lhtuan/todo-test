package com.test.todo.domain.models

import java.net.SocketTimeoutException
import javax.net.ssl.SSLPeerUnverifiedException

sealed class Failure {
    object SocketTimeout : Failure()
    object NotFound : Failure()
    object GoneError : Failure()
    object UnknownError : Failure()
    object InvalidPhoneNumber : Failure()
    object InvalidEmailAddress : Failure()
    object ReadFileError : Failure()
    object ProfileNotFound : Failure()
    object InvalidCertificate: Failure()
    object ConflictError : Failure()
    object NoNetworkError: Failure()

    companion object {
        //TODO handle http error here
        fun failureFromCode(httpCode: Int): Failure {
            return when (httpCode) {
                404 -> NotFound
                409 -> ConflictError
                410 -> GoneError
                else -> UnknownError
            }
        }

        //TODO handle http error here
        fun failureFromThrowable(throwable: Throwable): Failure {
            return when (throwable) {
                is SSLPeerUnverifiedException -> InvalidCertificate
                is SocketTimeoutException -> SocketTimeout
                else -> UnknownError
            }
        }
    }
}