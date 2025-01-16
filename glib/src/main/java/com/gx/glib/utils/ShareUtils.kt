package com.gx.glib.utils

import android.content.Context
import android.content.Intent

object ShareUtils {

    fun shareText(context:Context, shareText:String){
        val textIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,shareText)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(textIntent,null)
        context.startActivity(shareIntent)
    }

}