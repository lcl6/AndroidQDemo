package com.example.androidqdemo.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

/**
 *Created by liancl on 2020/11/16 0016.
 */

class MyTextView : AppCompatTextView{

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr)


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("MyTextView","onTouchEvent");
        return super.onTouchEvent(event)
//        return true
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.e("MyTextView","dispatchTouchEvent");
        return super.dispatchTouchEvent(event)
//        return true
//        return false
    }

}