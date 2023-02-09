package com.test.todo.presentation.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.todo.R
import com.test.todo.databinding.FragmentTasksBinding
import com.test.todo.domain.models.ResultState
import com.test.todo.domain.models.Task
import com.test.todo.presentation.base.BaseFragment
import com.test.todo.presentation.extensions.observe
import com.test.todo.utils.DataUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class TasksFragment : BaseFragment<FragmentTasksBinding>() {
    private val viewModel by viewModel<TaskViewModel>()
    private val taskAdapter = TaskAdapter()
    private val dateSelectorAdapter = DateSelectorAdapter().apply {
        onDateSelected = {
            viewModel.setSelectedDate(it)
        }
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentTasksBinding {
        return FragmentTasksBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskAdapter
        }

        binding.apply {
            tvTitle.text = DataUtil.getCurrentDate()

            rvDateSelector.adapter = dateSelectorAdapter
            rvDateSelector.clipToOutline = true

            btnAdd.setOnClickListener { findNavController().navigate(R.id.action_task_to_new) }
        }
        
        observe(viewModel.tasks, ::bindTasks)
        observe(viewModel.dates, ::bindDates)
    }

    private fun bindTasks(users: ResultState<List<Task>>?) {
        users?.data?.let {
            taskAdapter.items = it.toMutableList()
        }
    }

    private fun bindDates(date: List<Calendar>?) {
        date?.let {
            dateSelectorAdapter.items = it.toMutableList()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTasks()
    }
}
