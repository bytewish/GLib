package com.gx.glib.utils

object TextUtils {

    /**
     * Check if all characters are digits;
     * return false for an empty string
     */
    fun isAllDigits(input:String?):Boolean{
        return input?.all { it.isDigit() } ?: false
    }

}