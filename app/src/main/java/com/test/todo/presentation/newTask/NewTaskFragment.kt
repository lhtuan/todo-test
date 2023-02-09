package com.test.todo.presentation.newTask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.test.todo.R
import com.test.todo.databinding.FragmentNewTaskBinding
import com.test.todo.databinding.ItemCategoryChipBinding
import com.test.todo.domain.models.Category
import com.test.todo.domain.models.ResultState
import com.test.todo.domain.usecases.CreateTaskUseCase
import com.test.todo.presentation.base.BaseFragment
import com.test.todo.presentation.extensions.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class NewTaskFragment : BaseFragment<FragmentNewTaskBinding>() {
    private val viewModel by viewModel<NewTaskViewModel>()
    private val dateFormat = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentNewTaskBinding {
        return FragmentNewTaskBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(binding.toolbar)

        binding.edtTitle.setText(viewModel.title.value)
        binding.edtDesc.setText(viewModel.desc.value)

        binding.edtTitle.doAfterTextChanged {
            viewModel.setTitle(it.toString())
        }
        binding.edtDate.setOnClickListener {
            pickDate()
        }
        binding.edtFrom.setOnClickListener {
            pickFrom()
        }
        binding.edtTo.setOnClickListener {
            pickTo()
        }
        binding.edtDesc.doAfterTextChanged {
            viewModel.setDescription(it.toString())
        }
        binding.btnCreate.setOnClickListener {
            viewModel.createTask()
        }
        observe(viewModel.date) {
            binding.edtDate.setText(dateFormat.format(it.timeInMillis))
        }
        observe(viewModel.from) {
            binding.edtFrom.setText(timeFormat.format(it.timeInMillis))
        }
        observe(viewModel.to) {
            binding.edtTo.setText(timeFormat.format(it.timeInMillis))
        }
        observe(viewModel.categories, ::bindCategories)
        observe(viewModel.createTaskResult, ::bindResult)
    }

    private fun bindCategories(categories: List<Category>) {
        binding.categories.removeAllViews()
        categories.forEach {
            val chip = ItemCategoryChipBinding.inflate(LayoutInflater.from(context)).apply {
                root.text = it.name
                root.tag = it.id
            }
            chip.root.setOnClickListener {
                it.isSelected = !it.isSelected
                val id = it.tag as Long
                if (it.isSelected) {
                    viewModel.selectCategory(id)
                } else {
                    viewModel.unSelectCategory(id)
                }
            }
            binding.categories.addView(chip.root)
        }
    }

    private fun pickDate() {
        val currentDate = viewModel.date.value
        DatePickerDialog(requireContext())
            .apply {
                updateDate(
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH)
                )
                setOnDateSetListener { _, year, month, dayOfMonth ->
                    viewModel.setDate(year, month, dayOfMonth)
                }
            }.show()
    }

    private fun pickFrom() {
        val currentFrom = viewModel.from.value
        TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                viewModel.setFrom(hourOfDay, minute)
            }, currentFrom.get(Calendar.HOUR_OF_DAY), currentFrom.get(Calendar.MINUTE), true
        ).show()
    }

    private fun pickTo() {
        val currentTo = viewModel.to.value
        TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                viewModel.setTo(hourOfDay, minute)
            }, currentTo.get(Calendar.HOUR_OF_DAY), currentTo.get(Calendar.MINUTE), true
        ).show()
    }

    private fun bindResult(result: ResultState<Int>?) {
        if (result is ResultState.Loading) {
            binding.btnCreate.isEnabled = false
        } else if (result is ResultState.Success) {
            binding.btnCreate.isEnabled = true
            when (result.data) {
                CreateTaskUseCase.Out.SUCCESS -> {
                    findNavController().popBackStack()
                }
                CreateTaskUseCase.Out.ERROR_MISSING_FIELD -> {
                    showError(getString(R.string.error_missed_field))
                }
                CreateTaskUseCase.Out.ERROR_NO_CATEGORY -> {
                    showError(getString(R.string.error_category_required))
                }
                CreateTaskUseCase.Out.ERROR_INVALID_END_TIME -> {
                    showError(getString(R.string.error_invalid_end_time))
                }
            }
        }
    }

    private fun showError(err: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(err)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }
}