package com.gx.glib.utils

import android.util.Log
import java.text.SimpleDateFormat

/**
 * Get time jor format time
 */
object GTimeUtils {

    fun autoParseUTCTime(utcTimeStr: String) : SimpleDateFormat {
        return if(utcTimeStr.length > 20){
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
        }else{
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        }
    }

}