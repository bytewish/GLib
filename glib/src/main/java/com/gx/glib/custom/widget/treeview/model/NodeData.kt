package com.gx.glib.custom.widget.treeview.model

import android.os.Bundle

interface NodeData<T> {
    fun getNodeChild(): List<NodeData<T>>

    val nodeViewId: String

    fun areItemsTheSame(item: NodeData<T>): Boolean

    fun areContentsTheSame(item: NodeData<T>): Boolean

    fun getChangePayload(item: NodeData<T>): Bundle
}