package com.gx.glib.utils

object TextUtils {

    /**
     * Check if all characters are digits;
     * return false for an empty string
     */
    fun isAllDigits(input:String?):Boolean{
        return input?.all { it.isDigit() } ?: false
    }

    /**
     * Check whether the text contains non-empty characters
     * validSpace: true means space will be regarded as valid characters
     */
    fun isNotEmpty(input: String?,validSpace:Boolean):Boolean{
        if(!validSpace){
            input?.replace(" ","")
        }

        return !android.text.TextUtils.isEmpty(input)
    }

}