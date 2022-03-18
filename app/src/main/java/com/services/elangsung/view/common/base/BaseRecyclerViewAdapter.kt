package com.services.elangsung.view.common.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerViewAdapter<T>(
    protected val items: MutableList<T>,
    private val layoutRes: Int,
    protected open var renderConfig: ((T, View) -> Unit)? = null,
    protected open var onItemClicked: ((T, Int) -> Unit)? = null
) : RecyclerView.Adapter<BaseRecyclerViewViewHolder<T>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewViewHolder<T> {
        val view = onCreateView(LayoutInflater.from(parent.context), parent)
        return BaseRecyclerViewViewHolder.newInstance(view)
    }

    protected open fun onCreateView(inflater: LayoutInflater, parent: ViewGroup): View {
        return inflater.inflate(layoutRes, parent, false)
    }

    override fun getItemCount() = items.size

    open fun setItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseRecyclerViewViewHolder<T>, position: Int) {
        holder.render(items[position], renderConfig, onItemClicked, position)
    }
}
