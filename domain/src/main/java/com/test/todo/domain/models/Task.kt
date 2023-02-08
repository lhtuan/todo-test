package com.test.todo.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Long,
    val title: String,
    val date: String,
    val from: String,
    val to: String,
    val description: String,
    val categories: List<Category>
): Parcelable