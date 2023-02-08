package com.test.todo.presentation.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.todo.presentation.base.AutoUpdatableAdapter
import com.test.todo.R
import com.test.todo.databinding.ItemTaskBinding
import com.test.todo.domain.models.Task

class TaskAdapter : AutoUpdatableAdapter<Task, TaskAdapter.UserHolder>() {
    var onUserClick: ((Task) -> Unit)? = null

    override fun compareItem(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun compareContent(oldItem: Task, newItem: Task): Boolean {
        return oldItem.title == newItem.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        ) {
            onUserClick?.invoke(items[it])
        }
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(items[position])
    }

    class UserHolder(view: View, onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTaskBinding.bind(view)

        init {
            itemView.setOnClickListener {
                onItemClick(layoutPosition)
            }
        }

        fun bind(task: Task) {
            binding.apply {

            }
        }
    }
}