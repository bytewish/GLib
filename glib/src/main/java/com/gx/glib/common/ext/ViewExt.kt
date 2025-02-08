package com.gx.glib.common.ext

import android.view.View
import com.gx.glib.common.listener.SafeClickListener


fun View.setSafeOnClickListener(interval:Long = 1000,onSafeCall: (View) -> Unit){
    val safeClickListener = SafeClickListener(interval,onSafeCall)
    setOnClickListener(safeClickListener)
}