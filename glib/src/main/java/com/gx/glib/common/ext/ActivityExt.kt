package com.gx.glib.common.ext

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.RequiresApi

/**
 * This is an extension function for Activity
 */


/**
 * control the FullScreen
 */
fun Activity.setFullScreen(){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
        setFullScreenAbove30()
    }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        setFullScreenAbove21()
    }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
        setFullScreenAbove19()
    }
}

@RequiresApi(Build.VERSION_CODES.R)
private fun Activity.setFullScreenAbove30(){
    window.run {
        setDecorFitsSystemWindows(false)
        window.insetsController?.let { control ->
            control.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            control.hide(WindowInsets.Type.statusBars())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
private fun Activity.setFullScreenAbove21(){
    window.run {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = Color.TRANSPARENT
    }
}

private fun Activity.setFullScreenAbove19(){
    window.run {
        addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}