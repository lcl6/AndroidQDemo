package com.example.androidqdemo.flutter

import android.app.Activity
import android.text.TextUtils
import android.util.ArrayMap
import android.util.Log
import com.example.androidqdemo.App
import com.example.androidqdemo.ac.ViewActivity
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler

/**
 * flutter 调用原生方法
 *Created by liancl on 2021/12/16.
 */
class JavaMethodChannel( messenger:BinaryMessenger) : MethodChannel(messenger, "com.example.flutter_module"), MethodCallHandler {
    private var activity: Activity? = null

    override fun onMethodCall(methodCall: MethodCall, result: Result) {
        val name: String = methodCall.method
        Log.e(" method",methodCall.method );
        when(name){
            "open_view"-> Log.e("","open_view success");
        }
        result.success(true)
    }


    /**
     * 和ac 绑定
     */
    fun attach(activity: Activity) {
        this.activity = activity
        attach()
    }
    private fun attach() {
        setMethodCallHandler(this)
        val args: MutableMap<String, Any> = ArrayMap()
        val proxyAddress = System.getProperty("http.proxyHost")
        val portStr = System.getProperty("http.proxyPort")
        if (!TextUtils.isEmpty(proxyAddress) && !TextUtils.isEmpty(portStr)) {
            args["proxy"] = "$proxyAddress:$portStr"
        }
//        args["show_log"] = BuildConfig.SHOW_LOG
        invokeMethod("initParam", args)
    }

}