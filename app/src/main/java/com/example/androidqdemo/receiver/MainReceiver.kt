package com.example.androidqdemo.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.androidqdemo.base.util.ToastUtils

/**
 *Created by liancl on 2020/11/25 0025.
 */

class MainReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("MainReceiver","我是宿主 我收到插件的广播了 可以退下了")
        context?.let { ToastUtils.show(it,"我是宿主 我收到插件的广播了 可以退下了") }
    }
}