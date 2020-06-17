package com.example.androidqdemo.ac

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R

/**
 * 沉浸状态栏
 * Created by liancl on 2020/6/17 0017.
 */
class StatusActivity : AppCompatActivity() {
    var clickNagobar = false
    var clickFull = true
    var changeTitlecolor = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)
        ButterKnife.bind(this)
    }
    @OnClick(R.id.tv_lower, R.id.tv_naga, R.id.tv_naga_expand, R.id.tv_full, R.id.tv_full_hide, R.id.tv_full_sitick, R.id.tv_full_sitick_nage, R.id.tv_window_color)
    fun onClick(v: View) {
        when (v.id) {
            R.id.tv_lower -> window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
            R.id.tv_naga -> {
                /*   SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION ----》布局会扩展到状态栏和导航栏    SYSTEM_UI_FLAG_HIDE_NAVIGATION ----》布局不会   */ //                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                window.decorView.systemUiVisibility = if (clickNagobar) View.SYSTEM_UI_FLAG_HIDE_NAVIGATION else View.SYSTEM_UI_FLAG_VISIBLE
                clickNagobar = !clickNagobar
            }
            R.id.tv_naga_expand ->  /*   SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION ----》布局会扩展到状态栏和导航栏 状态栏和导航栏还在    SYSTEM_UI_FLAG_HIDE_NAVIGATION ----》布局不会  导航栏不在 点击显示   */window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            R.id.tv_full -> {
                /*  全屏组合 点击布局会显示状态栏和导航栏  Flag 容易被清除*/window.decorView.systemUiVisibility = if (clickFull) View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION else View.SYSTEM_UI_FLAG_VISIBLE
                clickFull = !clickFull
            }
            R.id.tv_full_hide -> {
                /* 布局会藏到状态栏和导航栏下方  导航栏和状态栏不会消失*/window.decorView.systemUiVisibility = if (clickFull) View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN else View.SYSTEM_UI_FLAG_VISIBLE
                clickFull = !clickFull
            }
            R.id.tv_full_sitick ->  /* 用来修饰SYSTEM_UI_FLAG_FULLSCREEN 和SYSTEM_UI_FLAG_HIDE_NAVIGATION  与系统短暂交互后 flag 会短暂清除 不久后自动回复  */window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            R.id.tv_full_sitick_nage ->  /* 用来修饰 SYSTEM_UI_FLAG_HIDE_NAVIGATION 与系统短暂交互后 flag 会短暂清除 不久后自动回复  */window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            R.id.tv_window_color -> {
                /* SYSTEM_UI_FLAG_LIGHT_STATUS_BAR  颜色变为黑色*/if (changeTitlecolor) { //设置字体颜色
                    window.statusBarColor = Color.TRANSPARENT
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else { //设置状态栏背景色
                    window.statusBarColor = R.color.colorPrimary
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                }
                changeTitlecolor = !changeTitlecolor
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, StatusActivity::class.java)
            context.startActivity(starter)
        }
    }
}