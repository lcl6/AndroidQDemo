package com.example.androidqdemo.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import kotlin.math.pow

/**
 *
 *
 *Created by liancl on 2021/9/24.
 */

class GoodsView : View {

    private var mRadius=20f
    //小红点开始坐标
    var mCircleStartPoint =  Point();
    //小红点结束坐标
    var mCircleEndPoint =  Point();
    //小红点控制点坐标
    var mCircleConPoint =  Point();
    //小红点的移动坐标
    private var mCircleMovePoint =  Point();
    private lateinit var mCirclePaint: Paint

    constructor(context: Context?) : super(context){
        init(context)
    }

    private fun init(context: Context?) {
        mCirclePaint =  Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.style = Paint.Style.FILL;
        mCirclePaint.color = Color.RED;

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas != null) {
            drawCircle(canvas)
        }
    }
    private fun drawCircle( canvas: Canvas) {
        canvas.drawCircle(mCircleMovePoint.x.toFloat(), mCircleMovePoint.y.toFloat(), mRadius, mCirclePaint);
    }

    fun startAnimation(){
        //设置控制点，控制点坐标直接影响曲线地样子
        mCircleConPoint.x = ((mCircleStartPoint.x + mCircleEndPoint.x) / 2);
        mCircleConPoint.y = (420);
        //设置属性动画
        var valueAnimator = ValueAnimator.ofObject( CirclePointEvaluator(mCircleConPoint), mCircleStartPoint, mCircleEndPoint);
        //动画执行时间
        valueAnimator.duration = 600;
        //非线性执行插值器
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.addUpdateListener {
            var goodsViewPoint = it.animatedValue as Point
            mCircleMovePoint.x = goodsViewPoint.x;
            mCircleMovePoint.y = goodsViewPoint.y;
            //重绘
            invalidate();
        };
        //动画结束回调，remove掉动画View
        valueAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                val viewGroup:ViewGroup = parent as ViewGroup;
                viewGroup.removeView(this@GoodsView);
            }
        })
        valueAnimator.start();

    }

    fun setCircleStartPoint(goodsPointX: Int, goodsPointY: Int) {
        mCircleStartPoint.x=goodsPointX;
        mCircleStartPoint.y=goodsPointY
    }

    fun setCircleEndPoint(shoppingCartPointX: Int,shoppingCartPointY: Int) {
        mCircleEndPoint.x=shoppingCartPointX
        mCircleEndPoint.y=shoppingCartPointY
    }

     class CirclePointEvaluator(var circleConPoint :Point) : TypeEvaluator<Point> {
        override fun evaluate(t: Float, startValue: Point?, endValue: Point?): Point {
            val startPoint = startValue as Point
            val endPoint = endValue as Point
            //直接套公式即可
            var x =  (((1 - t).toDouble()).pow(2.0) *startPoint.x+2*(1-t)*t*circleConPoint.x+ t.toDouble()
                .pow(2.0) *endPoint.x)
            var y =  (((1 - t).toDouble()).pow(2.0) *startPoint.y+2*(1-t)*t*circleConPoint.y+Math.pow(t.toDouble(), 2.0)*endPoint.y)
            return Point(x.toInt(), y.toInt())

        }

    }
}