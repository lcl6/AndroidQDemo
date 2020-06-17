package com.example.androidqdemo.base.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Message
import android.view.Gravity
import android.widget.Toast
import java.lang.reflect.Field

/**
 * 单例Toast工具类
 *
 *
 * 1.解决toast排队的问题
 * 2.修复Toast在android 7.1手机上的BadTokenException
 * 3.兼容位置、时长、stringId
 */
object ToastUtils {
    private var mToast: Toast? = null
    private var mFieldTN: Field? = null
    private var mFieldTNHandler: Field? = null
    /**
     * 初始化/获取mToast对象，适配android 7.1，处理BadTokenException
     *
     * @param context Context
     * @return Toast
     */
    @SuppressLint("ShowToast")
    private fun initToast(context: Context): Toast? {
        if (mToast == null) {
            mToast = Toast.makeText(context.applicationContext, "", Toast.LENGTH_SHORT)
            if (Build.VERSION.SDK_INT == 25) {
                hook(mToast)
            }
        }
        return mToast
    }

    /**
     * Toast位置显示在屏幕中间 默认短时长[Toast.LENGTH_SHORT]
     *
     * @param context Context
     * @param content 显示内容
     */
    fun showInCenter(context: Context, content: String?) {
        val toast = initToast(context)
        toast!!.setText(content)
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
    /**
     * show Toast 可选时长
     *
     * @param context  Context
     * @param message  内容
     * @param duration [Toast.LENGTH_SHORT],[Toast.LENGTH_LONG]
     */
    /**
     * show Toast 默认短时长 [Toast.LENGTH_SHORT]
     *
     * @param context Context
     * @param message 内容
     */
    @JvmOverloads
    fun show(context: Context, message: String?, duration: Int = Toast.LENGTH_SHORT) {
        val toast = initToast(context)
        toast!!.duration = duration
        toast.setText(message)
        mToast!!.show()
    }
    /**
     * show Toast 可选时长
     *
     * @param context  Context
     * @param stringId 内容id
     * @param duration [Toast.LENGTH_SHORT],[Toast.LENGTH_LONG]
     */
    /**
     * show Toast 默认短时长 [Toast.LENGTH_SHORT]
     *
     * @param context  Context
     * @param stringId 内容id
     */
    @JvmOverloads
    fun show(context: Context, stringId: Int, duration: Int = Toast.LENGTH_SHORT) {
        val toast = initToast(context)
        toast!!.duration = duration
        toast.setText(stringId)
        mToast!!.show()
    }

    /**
     * show Toast 可选位置
     *
     * @param context  Context
     * @param message  内容
     * @param duration [Toast.LENGTH_SHORT],[Toast.LENGTH_LONG]
     */
    fun show(context: Context, message: String?, gravity: Int, duration: Int) {
        val toast = initToast(context)
        toast!!.duration = duration
        toast.setText(message)
        toast.setGravity(gravity, 0, 0)
        mToast!!.show()
    }

    /**
     * 7.1手机上的BadTokenException 相关处理
     *
     * @param toast Toast对象
     */
    private fun hook(toast: Toast?) {
        try {
            val tn = mFieldTN!![toast]
            val preHandler = mFieldTNHandler!![tn] as Handler
            mFieldTNHandler!![tn] = FiexHandler(preHandler)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 7.1手机上的BadTokenException 相关处理
     */
    private class FiexHandler internal constructor(private val impl: Handler) : Handler() {
        override fun dispatchMessage(msg: Message) {
            try {
                super.dispatchMessage(msg)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun handleMessage(msg: Message) {
            impl.handleMessage(msg)
        }

    }

    init {
        if (Build.VERSION.SDK_INT == 25) {
            try {
                mFieldTN = Toast::class.java.getDeclaredField("mTN")
                mFieldTN!!.setAccessible(true)
                mFieldTNHandler = mFieldTN!!.getType().getDeclaredField("mHandler")
                mFieldTNHandler!!.isAccessible = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}