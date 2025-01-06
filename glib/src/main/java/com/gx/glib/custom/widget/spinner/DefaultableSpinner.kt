package com.gx.glib.custom.widget.spinner

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatAutoCompleteTextView

/**
 * It's a Spinner that allows showing a hint text before user select
 * similar to the html select
 */
class DefaultableSpinner (context: Context,attrs:AttributeSet):AppCompatAutoCompleteTextView(context,attrs){

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet){
        isFocusable = false
        isFocusableInTouchMode = false
        inputType = 0
        setOnTouchListener(object : OnTouchListener{
            override fun onTouch(v: View?, p1: MotionEvent?): Boolean {
                showDropDown()
                return v?.performClick() ?: false
            }
        })
    }
}