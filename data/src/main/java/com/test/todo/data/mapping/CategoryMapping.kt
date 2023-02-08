package com.test.todo.data.mapping

import com.test.todo.data.local.tables.CategoryTbl
import com.test.todo.domain.models.Category

fun CategoryTbl.toModel() = Category(
    id = categoryId,
    name = name
)

fun Category.toTable() = CategoryTbl(
    categoryId = id,
    name = name
)