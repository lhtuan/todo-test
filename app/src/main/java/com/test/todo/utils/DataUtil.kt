package com.test.todo.utils

import java.text.SimpleDateFormat
import java.util.*

object DataUtil {
    fun getCurrentDate(): String {
        val format = SimpleDateFormat("MMM yyyy", Locale.getDefault())
        return format.format(Calendar.getInstance().timeInMillis)
    }
}