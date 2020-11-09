package com.example.androidqdemo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 *Created by liancl on 2020/11/9 0009.
 */

class GreyLinearlayout : LinearLayout {

    var grayValue: Float? =0F
    val paint = Paint()

    constructor(context: Context?): super(context){
        initGray(context)
    }
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs){
        initGray(context)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes){
        initGray(context)

    }


    fun initGray(context: Context?) {
        val colorMatrix = ColorMatrix()
        grayValue?.let { colorMatrix.setSaturation(it) }
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    public fun openGray(open: Boolean){
        grayValue = if (open) {
            0f
        }else{
            1f
        }
    }

    override fun onDraw(canvas: Canvas?) {

        canvas?.saveLayer(null,paint,Canvas.ALL_SAVE_FLAG)
        super.onDraw(canvas)

    }

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.saveLayer(null,paint,Canvas.ALL_SAVE_FLAG)
        super.dispatchDraw(canvas)
    }

}