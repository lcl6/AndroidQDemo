package com.example.pluginapp

import android.app.Application
import android.util.Log

/**
 *Created by liancl on 2020/11/24 0024.
 */

class PluginApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e("PluginApplication","onCreate()");

    }
}