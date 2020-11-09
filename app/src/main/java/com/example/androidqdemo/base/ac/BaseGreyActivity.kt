package com.example.androidqdemo.base.ac

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import com.example.androidqdemo.R
import com.example.androidqdemo.view.GreyLinearlayout
import com.lodz.android.corekt.anko.bindView
import com.lodz.android.pandora.base.activity.AbsActivity

/**
 *Created by liancl on 2020/11/9 0009.
 */


abstract class BaseGreyActivity : AbsActivity() {

    /** 内容布局 */
    private val mGreyContentLayout by bindView<GreyLinearlayout>(R.id.base_vg_content_grey)

    final override fun afterSetContentView() {
        super.afterSetContentView()
        if (isUseAnkoLayout()) {
            throw RuntimeException("you already use anko layout , please extends AbsActivity and use @UseAnkoLayout annotation")
        }
        setContainerView()
    }

     private fun setContainerView(){
         if (getGreyLayoutId() == 0) {
             throw RuntimeException("getGreyLayoutId must not be null")
             return
         }
         val view = LayoutInflater.from(this).inflate(getGreyLayoutId(), null)
         val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
         mGreyContentLayout.addView(view, layoutParams)
    }

    fun openGray(open: Boolean){
        mGreyContentLayout.openGray(open)
    }

    @LayoutRes
    protected abstract fun getGreyLayoutId(): Int

    @LayoutRes
    final override fun getAbsLayoutId(): Int = R.layout.base_content_grey

}