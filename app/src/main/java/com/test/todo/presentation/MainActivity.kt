package com.test.todo.presentation

import android.view.LayoutInflater
import com.test.todo.databinding.ActivityMainBinding
import com.test.todo.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity<ActivityMainBinding>() {
    private val mainViewModel by viewModel<MainViewModel>()
    
    override fun inflate(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }
}