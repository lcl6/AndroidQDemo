package com.example.pluginiml

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent

/**
 *Created by liancl on 2020/11/23 0023.
 */

interface ProxyActivityInterface {
    //生命周期的activity
    fun attach(activity: Activity)
    fun onCreate(savedInstanceState: Bundle?)

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()


    fun onTouchEvent(event: MotionEvent?): Boolean

    fun onBackPressed()
}