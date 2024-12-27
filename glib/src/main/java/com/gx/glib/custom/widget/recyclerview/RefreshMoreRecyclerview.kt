package com.gx.glib.custom.widget.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gx.glib.R
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * It's a RecyclerView contains refresh and load more
 */
class RefreshMoreRecyclerview : ConstraintLayout {

    private lateinit var smartRefreshLayout:SmartRefreshLayout
    private lateinit var recyclerview:RecyclerView

    private var isRefreshing = false
    private var isLoadMore = false

    constructor(context: Context) : this(context,null)
    constructor(context: Context,attributeSet: AttributeSet?) : this(context,attributeSet,0)
    constructor(context: Context,attributeSet: AttributeSet?,defStyleAttr:Int): super(context,attributeSet){
        initView()
    }

    fun initView(){
        LayoutInflater.from(context).inflate(R.layout.widget_refresh_more_recyclerview,this,true)
        smartRefreshLayout = findViewById(R.id.refresh_layout)
        recyclerview = findViewById(R.id.recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.VERTICAL
        }
    }

    fun bindAdapter(adapter:RecyclerView.Adapter<*>) : RefreshMoreRecyclerview {
        recyclerview.adapter = adapter
        return this
    }

    fun bindListener(onRefreshFunc:(() -> Unit)?=null,onLoadMoreFunc:(() -> Unit)? = null):RefreshMoreRecyclerview{
        smartRefreshLayout.setOnRefreshListener {
            isRefreshing = true
            onRefreshFunc?.invoke()
        }
        smartRefreshLayout.setOnLoadMoreListener {
            isLoadMore = true
            onLoadMoreFunc?.invoke()
        }
        return this
    }

    fun addItemDecoration(decoration:DividerItemDecoration):RefreshMoreRecyclerview{
        recyclerview.addItemDecoration(decoration)
        return this
    }

    fun finishLoad(){
        if(isRefreshing){
            isRefreshing = false
            smartRefreshLayout.finishRefresh()
        }
        if(isLoadMore){
            isLoadMore = false
            smartRefreshLayout.finishLoadMore()
        }
    }

    fun enableRefreshOrMore(canRefresh:Boolean?,canLoadMore:Boolean?):RefreshMoreRecyclerview{
        canRefresh?.let {
            smartRefreshLayout.setEnableRefresh(it)
        }
        canLoadMore?.let {
            smartRefreshLayout.setEnableLoadMore(it)
        }
        return this
    }

}