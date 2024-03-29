package com.example.androidqdemo.base.ac

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import com.example.androidqdemo.R
import com.example.androidqdemo.util.LogicUtils
import com.example.androidqdemo.view.GreyLinearlayout
import com.lodz.android.corekt.anko.bindView
import com.lodz.android.pandora.base.activity.AbsActivity
import kotlin.math.log

/**
 *Created by liancl on 2020/11/9 0009.
 */


abstract class BaseGreyActivity : AbsActivity() {

    /**
     * 开启变灰模式
     */
    private var mOpenGray: Boolean=false


    /** 内容布局 */
    private val mGreyContentLayout by bindView<LinearLayout>(R.id.base_vg_content_grey)

    final override fun afterSetContentView() {
        super.afterSetContentView()
        if (isUseAnkoLayout()) {
            throw RuntimeException("you already use anko layout , please extends AbsActivity and use @UseAnkoLayout annotation")
        }
        setContainerView()
        if (mOpenGray) {
            initGray()
        }

    }

    //实现变灰 dlalog 不能变灰 状态栏可以变色
    private fun initGray() {
        val paint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0f)
        paint.colorFilter = ColorMatrixColorFilter(cm)
        window.decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint)
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

    fun openGray(openGray: Boolean){
        this.mOpenGray=openGray;
    }

    //修改字体夜色为黑色
    fun change(){
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        this.let {
            if (viewGroup != null) {
                LogicUtils.findChildView(it,viewGroup)
            }
        }
    }

//    /**
//     * 实现变灰 dialog 可以边灰 状态栏不能变色
//     */
    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        if (mOpenGray) {
            if("FrameLayout" == name){
                val attributeCount = attrs.attributeCount
                for (i in 0 until attributeCount){
                    val attributeName = attrs.getAttributeName(i)
                    Log.e("attributeName",attributeName+"")
                    val attributeValue = attrs.getAttributeValue(i)
                    if("id" == attributeName){
                        val id = Integer.parseInt(attributeValue.substring(1))
                        val idValue = resources.getResourceName(id)
                        if("android:id/content" == idValue){
                            return GreyLinearlayout(getContext(), attrs)
                        }
                    }
                }
            }
        }
        return super.onCreateView(parent, name, context, attrs)

    }

    @LayoutRes
    protected abstract fun getGreyLayoutId(): Int

    @LayoutRes
    final override fun getAbsLayoutId(): Int = R.layout.base_content_grey

}