package com.test.todo.presentation.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
 */
fun <T> Fragment.observe(
    flow: Flow<T>,
    onReceived: (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                onReceived.invoke(it)
            }
        }
    }
}