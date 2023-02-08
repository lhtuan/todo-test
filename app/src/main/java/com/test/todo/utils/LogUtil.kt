package com.test.todo.utils

import android.util.Log
import com.test.todo.BuildConfig

object LogUtil {
    const val TAG = "template"
    const val SPACE = "               "
    private val ENABLE_LOG = BuildConfig.DEBUG
    private fun getStackTrace(): StackTraceElement {
        val stackTraces = Thread.currentThread().stackTrace
        return if (stackTraces.size > 3) {
            stackTraces[4]
        } else {
            stackTraces.last()
        }
    }

    fun enter() {
        if (ENABLE_LOG) {
            Log.d(TAG, "" + SPACE + getStackTrace())
        }
    }

    fun d(msg: String) {
        if (ENABLE_LOG) {
            Log.d(TAG, msg + SPACE + getStackTrace())
        }
    }

    fun i(msg: String) {
        if (ENABLE_LOG) {
            Log.i(TAG, msg + SPACE + getStackTrace())
        }
    }

    fun e(msg: String) {
        if (ENABLE_LOG) {
            Log.e(TAG, msg + SPACE + getStackTrace())
        }
    }

    fun e(e: Throwable) {
        if (ENABLE_LOG) {
            e.message?.let {
                e(it)
            }
        }
    }
}