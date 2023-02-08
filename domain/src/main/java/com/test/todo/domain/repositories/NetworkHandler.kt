package com.test.todo.domain.repositories

interface NetworkHandler {
    fun isNetworkConnected(): Boolean
}