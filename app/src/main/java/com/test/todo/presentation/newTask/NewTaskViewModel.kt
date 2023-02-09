package com.test.todo.presentation.newTask

import androidx.lifecycle.MutableLiveData
import com.test.todo.domain.models.Category
import com.test.todo.domain.models.ResultState
import com.test.todo.domain.usecases.CreateTaskUseCase
import com.test.todo.domain.usecases.GetCategoriesUseCase
import com.test.todo.domain.usecases.None
import com.test.todo.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*

class NewTaskViewModel(
    getCategoriesUseCase: GetCategoriesUseCase,
    private val createTaskUseCase: CreateTaskUseCase
) : BaseViewModel() {
    private val _categories = MutableStateFlow<List<Category>>(
        emptyList()
    )
    val categories: StateFlow<List<Category>> = _categories

    private var selectedCategories: MutableList<Long> = mutableListOf()

    private val _date = MutableStateFlow<Calendar>(Calendar.getInstance())
    val date: StateFlow<Calendar> = _date

    private val _from = MutableStateFlow<Calendar>(Calendar.getInstance())
    val from: StateFlow<Calendar> = _from

    private val _to = MutableStateFlow<Calendar>(Calendar.getInstance().apply {
        add(Calendar.HOUR_OF_DAY, 1)
    })
    val to: StateFlow<Calendar> = _to

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title

    private val _desc = MutableStateFlow("")
    val desc: StateFlow<String> = _desc

     private val _createTaskResult = MutableStateFlow<ResultState<Int>?>(null)
    val createTaskResult: StateFlow<ResultState<Int>?> = _createTaskResult

    init {
        getCategoriesUseCase(getScope(), None()) {
            _categories.value = it
        }
    }

    fun selectCategory(catId: Long) {
        if (!selectedCategories.contains(catId)) {
            selectedCategories.add(catId)
        }
    }

    fun unSelectCategory(catId: Long) {
        selectedCategories.remove(catId)
    }

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setDescription(description: String) {
        _desc.value = description
    }

    fun setDate(year: Int, month: Int, dayOfMonth: Int) {
        _date.value = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONDAY, month)
            set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    fun setFrom(hourOfDay: Int, minute: Int) {
        _from.value = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
        }
    }

    fun setTo(hourOfDay: Int, minute: Int) {
        _to.value = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hourOfDay)
            set(Calendar.MINUTE, minute)
        }
    }

    fun createTask() {
        _createTaskResult.value = ResultState.Loading()
        createTaskUseCase(getScope(), CreateTaskUseCase.In(
            title = _title.value,
            description = _desc.value,
            date = _date.value,
            from = _from.value,
            to = _to.value,
            categories = selectedCategories
        )) {
           _createTaskResult.value = ResultState.Success(it.result)
        }
    }
}