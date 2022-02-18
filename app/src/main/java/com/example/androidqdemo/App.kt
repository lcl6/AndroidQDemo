package com.example.androidqdemo

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Build
import android.os.Process
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.multidex.MultiDex
import com.example.androidqdemo.flutter.base.FlutterBoost
import com.example.androidqdemo.tinker.FlutterPatch
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import io.flutter.FlutterInjector


/**
 *Created by liancl on 2020/11/9 0009.
 */

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Companion.inst =this
        initBugly(this)
        initTinker()
        FlutterBoost.instance.init(this);
}
    companion object{
        private lateinit var inst :App;
        fun getInstance(): App{
            return inst
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        Beta.installTinker()
        Log.i("-----", "attachBaseContext")
    }
    private fun initBugly(application: Application) {


        //第三个参数为SDK调试模式开关，调试模式的行为特性如下：
        //    输出详细的Bugly SDK的Log；
        //    每一条Crash都会被立即上报；
        //    自定义日志将会在Logcat中输出。
        val context: Context = application
        // 获取当前包名
        val packageName = context.packageName
        // 获取当前进程名
        val processName = getProcessName(application)
        // 设置是否为上报进程
        val strategy = UserStrategy(context)
        strategy.isUploadProcess = processName == null || processName == packageName
        strategy.appReportDelay = 10000
        //第三个参数为SDK调试模式开关，调试模式的行为特性如下：
        //    输出详细的Bugly SDK的Log；
        //    每一条Crash都会被立即上报；
        //    自定义日志将会在Logcat中输出。
//        CrashReport.initCrashReport(context, "5ac63678e4", BuildConfig.DEBUG, strategy);
        Bugly.init(context, "81ab37e49d", true, strategy)
    }
    private fun getProcessName(context: Context?): String? {
        if (context == null) return null
        val manager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (processInfo in manager.runningAppProcesses) {
            if (processInfo.pid == Process.myPid()) {
                return processInfo.processName
            }
        }
        return null
    }

    private fun initTinker() {
        Log.i("-----", "initTinker")
        FlutterInjector.instance().flutterLoader().startInitialization(this)
        FlutterPatch.hook(this, "armeabi-v7a")
    }
}