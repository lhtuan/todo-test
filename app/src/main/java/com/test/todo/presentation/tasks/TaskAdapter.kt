package com.test.todo.presentation.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.todo.R
import com.test.todo.databinding.ItemTaskBinding
import com.test.todo.domain.Const
import com.test.todo.domain.models.Task
import com.test.todo.presentation.base.AutoUpdatableAdapter
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter : AutoUpdatableAdapter<Task, TaskAdapter.UserHolder>() {
    var onUserClick: ((Task) -> Unit)? = null
    val srcTimeFormat = SimpleDateFormat(Const.TASK_TIME_FORMAT, Locale.ENGLISH)
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

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

    inner class UserHolder(view: View, onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTaskBinding.bind(view)

        init {
            itemView.setOnClickListener {
                onItemClick(layoutPosition)
            }
        }

        fun bind(task: Task) {
            binding.apply {
                tvTitle.text = task.title
                val fromDate = srcTimeFormat.parse(task.from)
                val from = fromDate?.let {
                    timeFormat.format(fromDate.time)
                } ?: ""

                val toDate = srcTimeFormat.parse(task.to)
                val to = toDate?.let {
                    timeFormat.format(fromDate.time)
                } ?: ""

                tvDesc.text = "$from - $to"
            }
        }
    }
}