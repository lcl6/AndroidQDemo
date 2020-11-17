package com.example.androidqdemo.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 *Created by liancl on 2020/11/16 0016.
 */

class Mylinealayout :LinearLayout{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("Mylinealayout","onTouchEvent");
        return super.onTouchEvent(event)
//        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("Mylinealayout","onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev)
//        return false
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("Mylinealayout","dispatchTouchEvent");
//        return true
        return super.dispatchTouchEvent(ev)
    }
}