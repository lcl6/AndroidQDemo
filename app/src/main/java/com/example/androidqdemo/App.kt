package com.example.androidqdemo

import android.app.Application
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.view.View
import androidx.multidex.MultiDex


/**
 *Created by liancl on 2020/11/9 0009.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this);
        Companion.inst =this
    }
    companion object{
        private lateinit var inst :App;
        fun getInstance(): App{
            return inst
        }
    }

}