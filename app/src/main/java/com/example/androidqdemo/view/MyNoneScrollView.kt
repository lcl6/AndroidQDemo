package com.example.androidqdemo.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.MeasureSpec
import android.widget.ScrollView
import com.example.androidqdemo.util.ScreenUtils

/**
 *Created by liancl on 2020/11/16 0016.
 */

class MyNoneScrollView : ScrollView {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val mode = MeasureSpec.getMode(heightMeasureSpec)
//        if(mode== MeasureSpec.UNSPECIFIED){
//            Log.e("onMeasure2222"," MeasureSpec.UNSPECIFIED");
//        }else if(mode== MeasureSpec.EXACTLY){
//            Log.e("onMeasure2222"," MeasureSpec.EXACTLY");
//        }else if(mode== MeasureSpec.AT_MOST){
//            Log.e("onMeasure2222"," MeasureSpec.AT_MOST");
//        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//    }

//    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        Log.e("MyNoneScrollView","onInterceptTouchEvent")
////        return super.onInterceptTouchEvent(ev)
//        return true
//    }
//
//
//    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
//        Log.e("MyNoneScrollView","dispatchTouchEvent");
//        return super.dispatchTouchEvent(event)
//    }
//
//
//    override fun onTouchEvent(ev: MotionEvent?): Boolean {
//        Log.e("MyNoneScrollView","onTouchEvent");
//        return super.onTouchEvent(ev)
//    }




}