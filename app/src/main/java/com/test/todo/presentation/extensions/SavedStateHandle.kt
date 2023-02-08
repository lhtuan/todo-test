package com.test.todo.presentation.extensions

import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.getSafety(key: String, action: (T) -> Unit) {
    if(contains(key)) {
        get<T>(key)?.let {
            action.invoke(it)
        }
        remove<T>(key)
    }
}