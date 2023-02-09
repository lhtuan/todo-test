package com.test.todo.domain.usecases

import com.test.todo.domain.Const
import com.test.todo.domain.models.Task
import com.test.todo.domain.repositories.CategoryRepo
import com.test.todo.domain.repositories.TaskRepo
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskUseCase(
    dispatcherProvider: DispatcherProvider,
    private val taskRepo: TaskRepo,
    private val categoryRepo: CategoryRepo
) : BaseUseCase<CreateTaskUseCase.Out, CreateTaskUseCase.In>(dispatcherProvider) {
    override suspend fun run(params: In): Out {
        if (params.title.isBlank() || params.description.isBlank()) {
            return Out(result = Out.ERROR_MISSING_FIELD)
        } else if (params.to <= params.from) {
            return Out(result = Out.ERROR_INVALID_END_TIME)
        } else if (params.categories.isEmpty()) {
            return Out(result = Out.ERROR_NO_CATEGORY)
        } else {
            val task = Task(
                id = 0,
                title = params.title,
                description = params.description,
                date = SimpleDateFormat(
                    Const.TASK_DATE_FORMAT,
                    Locale.ENGLISH
                ).format(params.date.timeInMillis),
                from = SimpleDateFormat(
                    Const.TASK_TIME_FORMAT,
                    Locale.ENGLISH
                ).format(params.from.timeInMillis),
                to = SimpleDateFormat(
                    Const.TASK_TIME_FORMAT,
                    Locale.ENGLISH
                ).format(params.to.timeInMillis),
                categories = categoryRepo.getCategories()
                    .filter { c -> params.categories.contains(c.id) }
            )
            taskRepo.addTask(task)
            return Out(Out.SUCCESS)
        }
    }


    data class In(
        val title: String,
        val description: String,
        val date: Calendar,
        val from: Calendar,
        val to: Calendar,
        val categories: List<Long>
    )

    data class Out(
        val result: Int
    ) {
        companion object {
            const val SUCCESS = 0
            const val ERROR_MISSING_FIELD = 1
            const val ERROR_INVALID_END_TIME = 2
            const val ERROR_NO_CATEGORY = 3
        }
    }
}
