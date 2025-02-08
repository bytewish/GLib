package com.gx.glib.common.listener

import android.view.View

/**
 * Prevent Double Click
 */
class SafeClickListener(
    private val interval:Long = 1000,
    private val onSafeCall: (View) -> Unit
) : View.OnClickListener{

    private var lastClickTime:Long = 0L

    override fun onClick(v: View?) {
        val currentTime = System.currentTimeMillis()
        if(currentTime - lastClickTime >= interval){
            lastClickTime = currentTime
            v?.let { onSafeCall(it) }
        }
    }
}