package com.example.androidqdemo.util

import android.R
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi

/**
 * 屏幕信息帮助类
 */
object ScreenUtils {
    /**
     * 获得屏幕宽度
     *
     * @param context 上下文
     */
    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                ?: return 0
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    /**
     * 获得屏幕高度
     *
     * @param context 上下文
     */
    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                ?: return 0
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }

    /**
     * 判断是否存在NavigationBar
     */
    fun hasNavigationBar(window: Window): Boolean {
        val decorViewHeight = window.decorView.height
        val dm = DisplayMetrics()
        window.windowManager.defaultDisplay.getMetrics(dm)
        val useableScreenHeight = dm.heightPixels
        return decorViewHeight != useableScreenHeight
    }

    /**
     * 判断是否存在NavigationBar
     */
    fun hasNavigationBar(activity: Activity): Boolean {
        return hasNavigationBar(activity.window)
    }

    /**
     * 获取虚拟按键高度
     */
    fun getNavigationBarHeight(context: Context, window: Window): Int {
        val resources = context.resources
        val id = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (id > 0 && hasNavigationBar(window)) {
            resources.getDimensionPixelSize(id)
        } else 0
    }

    /**
     * 获取虚拟按键高度
     */
    fun getNavigationBarHeight(activity: Activity): Int {
        return getNavigationBarHeight(activity, activity.window)
    }

    /**
     * 获取状态栏高度
     *
     * @param context 上下文
     */
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else 0
    }

    /**
     * 当前是否是横屏
     * @return true：是横屏；false：是竖屏
     */
    fun isWideScrren(context: Context): Boolean {
        val mWindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = mWindowManager.defaultDisplay
        val pt = Point()
        display.getSize(pt)
        return pt.x > pt.y
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    internal fun hideBottomUIMenu(activity: Activity, hide: Boolean) {

        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = activity.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView = activity.window.decorView
            if (hide) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or  // hide nav bar
                        View.SYSTEM_UI_FLAG_FULLSCREEN
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
        }
    }

    fun showNavigationBar(activity: Activity) {
        val decorView = activity.window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun hideNavigationBar(activity: Activity) {
        var uiFlags = ( //                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        or View.SYSTEM_UI_FLAG_FULLSCREEN) // hide status bar
        uiFlags = if (Build.VERSION.SDK_INT >= 19) {
            uiFlags or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY //SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars - compatibility: building API level is lower thatn 19, use magic number directly for higher API target level
        } else {
            uiFlags or View.SYSTEM_UI_FLAG_LOW_PROFILE
        }
        activity.window.decorView.systemUiVisibility = uiFlags
        //设置这个得话 editext 字数一多 就会顶上布局
        //解决弹出键盘时 显示状态栏
        activity.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    /**
     * 可以利用getScreenHeight() 与 getScreenHeight3() 返回值的差异来判断是否存在虚拟导航栏
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun hasNavigationBar(context: Context): Boolean {
        return getScreenHeight1(context) != getScreenHeightIncludeNavigationBar(context)
    }

    /**
     * 包含虚拟导航栏高度
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun getScreenWidthIncludeNavigationBar(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay = windowManager.defaultDisplay
        val outPoint = Point()
        defaultDisplay.getRealSize(outPoint)
        return outPoint.x
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun getScreenHeightIncludeNavigationBar(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val defaultDisplay = windowManager.defaultDisplay
        val outPoint = Point()
        defaultDisplay.getRealSize(outPoint)
        return outPoint.y
    }

    fun getScreenHeight1(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    //全屏切换到非全屏 布局下移
    fun smoothScreen(activity: Activity) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            val rootView = activity.findViewById<View>(R.id.content) as ViewGroup
            val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
            val statusBarHeight = activity.resources.getDimensionPixelSize(resourceId)
            rootView.setPadding(0, statusBarHeight, 0, 0)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    //如果是横屏
    fun isLandScreen(activity: Activity): Boolean {
        return activity.resources.configuration.orientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    } //隐藏状态栏
}