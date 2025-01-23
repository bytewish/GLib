package com.gx.glib.common.definitions

interface CallBack{
    fun onCall()
}

interface BooleanCallback {
    fun onCall(status:Boolean)
}

interface IntCallback {
    fun onCall(num:Int)
}