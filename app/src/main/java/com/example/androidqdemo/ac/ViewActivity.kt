package com.example.androidqdemo.ac

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androidqdemo.R
import com.example.androidqdemo.view.GoodsView
import java.io.File


/**
 * 自定义view
 * Created by liancl on 2020/6/17 0017.
 */
class ViewActivity : AppCompatActivity() {
    private lateinit var tvClick:TextView
    private lateinit var tvReceive:TextView
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val mViewGroup = window.decorView as ViewGroup

        tvClick  = findViewById<TextView>(R.id.tv_click)
        tvReceive  = findViewById<TextView>(R.id.tv_receive)
        tvClick.setOnClickListener {
            //获取商品坐标
            val goodsPoint = IntArray(2)
            tvClick.getLocationInWindow(goodsPoint)
            //获取购物车坐标
            val shoppingCartPoint = IntArray(2)
            tvReceive.getLocationInWindow(shoppingCartPoint)

            val goodsView = GoodsView(this@ViewActivity)
            goodsView.setCircleStartPoint(goodsPoint[0], goodsPoint[1]);
            goodsView.setCircleEndPoint(shoppingCartPoint[0] + tvClick.width / 2, shoppingCartPoint[1]);
            //添加View并执行动画
            mViewGroup.addView(goodsView);
            goodsView.startAnimation();

        }
    }


    companion object {
        fun start(context: Context) {
            val starter = Intent(context, ViewActivity::class.java)
            context.startActivity(starter)
        }
    }
}