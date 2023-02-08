package com.test.todo.presentation.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.test.todo.R
import com.test.todo.databinding.ItemDateSelectorBinding
import com.test.todo.presentation.base.AutoUpdatableAdapter
import java.text.SimpleDateFormat
import java.util.*

class DateSelectorAdapter : AutoUpdatableAdapter<Calendar, DateSelectorAdapter.DateSelectorVH>() {
    private val dateFormat = SimpleDateFormat("EEE", Locale.getDefault())
    private var selectedDate: Long = 0
    var onDateSelected: ((Calendar) -> Unit)? = null

    override fun compareItem(oldItem: Calendar, newItem: Calendar): Boolean {
        return oldItem.timeInMillis == newItem.timeInMillis
    }

    override fun compareContent(oldItem: Calendar, newItem: Calendar): Boolean {
        return oldItem.timeInMillis == newItem.timeInMillis
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateSelectorVH {
        return DateSelectorVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_date_selector, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DateSelectorVH, position: Int) {
        holder.bind(items[position])
    }

    inner class DateSelectorVH(view: View) : ViewHolder(view), View.OnClickListener {
        private val binding = ItemDateSelectorBinding.bind(view)

        init {
            view.setOnClickListener(this)
        }

        fun bind(item: Calendar) {
            binding.apply {
                tvWeekDay.text = dateFormat.format(item.timeInMillis)
                tvDate.text = item.get(Calendar.DAY_OF_MONTH).toString()
                root.isSelected = if (selectedDate == 0L) {
                    layoutPosition == 0
                } else {
                    item.timeInMillis == selectedDate
                }
            }
        }

        override fun onClick(p0: View?) {
            onDateSelected?.invoke(items[layoutPosition])
            selectedDate = items[layoutPosition].timeInMillis
            notifyDataSetChanged()
        }
    }
}