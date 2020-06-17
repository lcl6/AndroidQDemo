package com.example.androidqdemo.base.dg

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.example.androidqdemo.R

/**
 * 弹框基类
 * Created by zhouL on 2016/12/7.
 */
abstract class BaseDialog : Dialog {
    constructor(context: Context) : super(context, R.style.BaseDialog) {
        initDialog(context)
    }

    constructor(context: Context, @StyleRes themeResId: Int) : super(context, themeResId) {
        initDialog(context)
    }

    private fun initDialog(context: Context) {
        onStartInit(context)
        setContentView(layoutId)
        findViews()
        setListeners()
        initData()
        setWindowAnimations()
    }

    protected fun onStartInit(context: Context?) {}
    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract fun findViews()
    protected fun setListeners() {}
    protected fun initData() {}
    @get:StyleRes
    protected open val animations: Int
        protected get() = -1

    private fun setWindowAnimations() {
        if (window != null && animations != -1) {
            window!!.setWindowAnimations(animations) //设置窗口弹出动画
        }
    }

    /**
     * 设置阴影
     * @param elevation 阴影值
     * @param background 背景（需要设置背景才能设置阴影）
     */
    protected fun setElevation(elevation: Float, background: Drawable?) {
        if (background == null) {
            return
        }
        if (window == null) {
            return
        }
        if (window!!.decorView == null) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window!!.decorView.elevation = elevation
            window!!.decorView.background = background
        }
    }

    protected val dialogInterface: DialogInterface
        protected get() = this
}