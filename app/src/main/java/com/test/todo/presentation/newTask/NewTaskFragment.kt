package com.test.todo.presentation.newTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.todo.databinding.FragmentNewTaskBinding
import com.test.todo.presentation.base.BaseFragment

class NewTaskFragment : BaseFragment<FragmentNewTaskBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentNewTaskBinding {
        return FragmentNewTaskBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(binding.toolbar)
    }
}