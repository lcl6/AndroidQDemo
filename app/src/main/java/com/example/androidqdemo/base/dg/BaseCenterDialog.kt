package com.example.androidqdemo.base.dg

import android.content.Context
import android.view.Gravity
import com.example.androidqdemo.R

/**
 * 中间弹框基类（缩放动画）
 * Created by zhouL on 2016/12/7.
 */
abstract class BaseCenterDialog : BaseDialog {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, themeResId: Int) : super(context, themeResId) {}


    /** 是否有动画  */
    protected fun hasAnimations(): Boolean {
        return true
    }

    override fun show() {
        val window = window
        window?.setGravity(Gravity.CENTER)
        super.show()
    }
}