package com.example.androidqdemo.flutter

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.util.ArrayMap
import android.util.Log
import com.example.androidqdemo.App
import com.example.androidqdemo.ac.ViewActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import java.io.Serializable
import java.util.HashMap

/**
 * flutter 调用原生方法
 *Created by liancl on 2021/12/16.
 */
class JavaMethodChannel( messenger:BinaryMessenger) : MethodChannel(messenger, "com.example.flutter_module"), MethodCallHandler {
    private var activity: Activity? = null
    private var fragment: FlutterFragment? = null

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
//        val args: MutableMap<String, Any> = ArrayMap()
//        val proxyAddress = System.getProperty("http.proxyHost")
//        val portStr = System.getProperty("http.proxyPort")
//        if (!TextUtils.isEmpty(proxyAddress) && !TextUtils.isEmpty(portStr)) {
//            args["proxy"] = "$proxyAddress:$portStr"
//        }
////        args["show_log"] = BuildConfig.SHOW_LOG
//        invokeMethod("initParam", args)
    }


    fun attach(fragment: FlutterFragment) {
        this.fragment = fragment
        this.attach()
    }


    fun detach() {
        activity = null
        fragment = null
        setMethodCallHandler(null)
    }


    /**
     * 传值 或者跳转
     */
    fun startRoute(routeName: String, forResult: Boolean, argument: Serializable?) {
        val args: MutableMap<String, Any> = if (argument != null) {
            argument as MutableMap<String, Any>
        } else {
            HashMap()
        }
        args["_#_route_#_"] = routeName //名字设置为偏僻点，降低重复的概率
        args["_#_result_#_"] = forResult
        val proxyAddress = System.getProperty("http.proxyHost")
        val portStr = System.getProperty("http.proxyPort")
        if (!TextUtils.isEmpty(proxyAddress) && !TextUtils.isEmpty(portStr)) {
            args["proxy"] = "$proxyAddress:$portStr"
        }
        invokeMethod("startRoute", args)
    }

    /**
     * ActivityResult 返回结果
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode != Activity.RESULT_OK) return
    }

}