package com.gx.glib.custom.widget.treeview.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gx.glib.custom.widget.treeview.GapoTreeView
import com.gx.glib.custom.widget.treeview.model.NodeViewData

internal class TreeViewAdapter<T>(
    private val layoutRes: Int,
    private val listener: GapoTreeView.Listener<T>
) : ListAdapter<NodeViewData<T>, TreeViewAdapter.NodeHolder<T>>(NodeViewData.DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return NodeHolder(view, listener)
    }

    override fun onBindViewHolder(holder: NodeHolder<T>, position: Int) {
        holder.bind(getItem(position), null)
    }

    internal class NodeHolder<T>(
        itemView: View,
        private val listener: GapoTreeView.Listener<T>,
    ) : RecyclerView.ViewHolder(itemView) {

        var nodeLevel = 0

        fun bind(item: NodeViewData<T>, bundle: Bundle?) = with(itemView) {
            nodeLevel = item.nodeLevel
            listener.onBind(this, absoluteAdapterPosition, item, bundle)
        }
    }

    override fun onBindViewHolder(
        holder: NodeHolder<T>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val bundle = payloads.firstOrNull() as? Bundle
        if (bundle == null) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.bind(getItem(position), bundle)
        }
    }
}
