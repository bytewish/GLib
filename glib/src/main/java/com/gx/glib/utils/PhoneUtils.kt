package com.gx.glib.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object DeviceUtils {

    /**
     * Get the screen width in px
     */
    fun getScreenWidthInPx(context: Context):Int{
        return getDisplayMetrics(context).widthPixels
    }

    /**
     * Get the screen height in px
     */
    fun getScreenWidthInDp(context: Context):Int{
        return unitPxToDp(context,getScreenWidthInPx(context))
    }

    /**
     * Unit conversion
     * px to dp
     */
    fun unitPxToDp(context: Context,pxValue:Int):Int{
        val density = context.resources.displayMetrics.density
        return (pxValue/density).toInt()
    }

    private fun getDisplayMetrics(context:Context):DisplayMetrics{
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics
    }

}