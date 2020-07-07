package com.example.androidqdemo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * 带进度的iamgeview
 * Created by liancl on 2020/7/6 0006.
 */
class ProgressImageView : AppCompatImageView {
    private var per = 0f
    private var isfinished = false
    private val colorStr: String? = null
    private var paintLayer: Paint? = null
    private var textPaint: Paint? = null
    private var textbound: Rect? = null
    private var layer_w = 0f
    private var layer_h = 0f

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    //初始化画笔
    private fun init() {
        paintLayer = Paint()
        paintLayer!!.color = Color.LTGRAY
        paintLayer!!.alpha = 100
        textPaint = Paint()
        textPaint!!.color = Color.DKGRAY
        textPaint!!.textSize = 25f
        textbound = Rect()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isfinished) return
        val perStr: String =( (per * 100) as Float).toString()  + "%"
        //获取文字区域的矩形大小，以便确定文字正中间的位置
        textPaint!!.getTextBounds(perStr, 0, perStr.length, textbound)
        layer_w = width.toFloat()
        layer_h = height * per
        val y = height - layer_h
        //画遮蔽层
        canvas.drawRect(0f, y, layer_w, height.toFloat(), paintLayer!!)
        //画文字
        canvas.drawText(perStr, width / 2 - textbound!!.width() / 2.toFloat(), height / 2 + textbound!!.height() / 2.toFloat(), textPaint!!)
    }

    fun setPer(per: Float) {
        this.per = per
        //在主线程刷新
        postInvalidate()
    }

    fun finish() {
        isfinished = true
        postInvalidate()
    }
}