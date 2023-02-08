package com.test.todo.presentation.base

import androidx.recyclerview.widget.DiffUtil
import kotlin.properties.Delegates

abstract class AutoUpdatableAdapter<V, VH> :
    androidx.recyclerview.widget.RecyclerView.Adapter<VH>() where VH : androidx.recyclerview.widget.RecyclerView.ViewHolder {

    var items: MutableList<V> by Delegates.observable(mutableListOf()) { _, oldList, newList ->
        autoNotify(
            oldList,
            newList,
            { i1, i2 -> compareItem(i1, i2) },
            { i1, i2 -> compareContent(i1, i2) }
        )
    }

    abstract fun compareItem(oldItem: V, newItem: V): Boolean

    abstract fun compareContent(oldItem: V, newItem: V): Boolean

    override fun getItemCount() = items.count()

    private inline fun androidx.recyclerview.widget.RecyclerView.Adapter<*>.autoNotify(
        oldList: List<V>,
        newList: List<V>,
        crossinline compareItem: (V, V) -> Boolean,
        crossinline compareContent: (V, V) -> Boolean
    ) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compareItem(oldList[oldItemPosition], newList[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compareContent(oldList[oldItemPosition], newList[newItemPosition])
            }

            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size
        })
        diff.dispatchUpdatesTo(this)
    }
}