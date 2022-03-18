package com.services.elangsung.view.common.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

open class BaseRecyclerViewViewHolder<T>(
    override val containerView: View
): RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun <T> newInstance(
            view: View
        ): BaseRecyclerViewViewHolder<T> {
            return BaseRecyclerViewViewHolder(view)
        }
    }

    open fun render(
            item: T,
            configView: ((T, View) -> Unit)?,
            onItemClicked: ((T, Int) -> Unit)?,
            position: Int
    ) {
        containerView.context ?: return
        configView?.invoke(item, containerView)
        containerView.setOnClickListener { onItemClicked?.invoke(item, position) }
    }
}
