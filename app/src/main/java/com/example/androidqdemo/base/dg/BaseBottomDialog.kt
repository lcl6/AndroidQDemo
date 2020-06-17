package com.example.androidqdemo.base.dg

import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import androidx.annotation.StyleRes
import com.example.androidqdemo.R

/**
 * 底部弹框基类
 * Created by zhouL on 2016/12/7.
 */
abstract class BaseBottomDialog : BaseDialog {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, @StyleRes themeResId: Int) : super(context, themeResId) {}

    protected override val animations: Int
        protected get() = R.style.animation_bottom_in_bottom_out

    override fun show() {
        val window = window
        if (window != null) {
            window.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
            if (isMatchWidth) {
                val layoutParams = window.attributes
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
                window.attributes = layoutParams
            }
        }
        super.show()
    }

    /** 是否需要填满宽度  */
    protected val isMatchWidth: Boolean
        protected get() = true
}