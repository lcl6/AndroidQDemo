package com.example.androidqdemo.base.util

import android.os.Handler
import android.os.Looper
import android.os.Message

/**
 * 把runnable post到UI线程执行的工具类
 * Created by zhouL on 2016/11/17.
 */
object UiHandler {
    private var sHandler: Handler? = null
    /** 初始化  */
    private fun prepare() {
        synchronized(UiHandler::class.java) {
            if (sHandler == null) {
                sHandler = Handler(Looper.getMainLooper())
            }
        }
    }

    /**
     * 在UI线程执行Runnable
     * @param r 线程体
     */
    fun post(r: () -> Unit) {
        prepare()
        if (Looper.myLooper() == Looper.getMainLooper()) {
            r.run(){

            }
        } else {
            sHandler!!.post(r)
        }
    }

    /**
     * 在UI线程执行Runnable，并指定token
     * @param r 线程体
     * @param token 标志
     */
    fun post(r: Runnable, token: Any?) {
        prepare()
        if (Looper.myLooper() == Looper.getMainLooper()) {
            r.run()
        } else {
            val message = Message.obtain(sHandler, r)
            message.obj = token
            sHandler!!.sendMessage(message)
        }
    }

    /**
     * 延迟执行
     * @param r 线程体
     * @param delay 延迟时间（毫秒）
     */
    fun postDelayed(r: Runnable?, delay: Long) {
        prepare()
        sHandler!!.postDelayed(r, delay)
    }

    /**
     * 延迟执行，并指定token
     * @param r 线程体
     * @param token 标志
     * @param delay
     */
    fun postDelayed(r: Runnable?, token: Any?, delay: Long) {
        prepare()
        val message = Message.obtain(sHandler, r)
        message.obj = token
        sHandler!!.sendMessageDelayed(message, delay)
    }

    /**
     * 移除指定token的Runnable（token传null则移除所有的Runnable）
     * @param token 标志
     */
    fun remove(token: Any?) {
        prepare()
        sHandler!!.removeCallbacksAndMessages(token)
    }

    /** 销毁Handler  */
    fun destroy() {
        remove(null)
        sHandler = null
    }
}