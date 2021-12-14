//package com.example.androidqdemo.manager.tinker
//
//import android.app.Application
//import android.content.Context
//import android.content.Intent
//import androidx.multidex.MultiDex
//import com.tencent.tinker.anno.DefaultLifeCycle
//import com.tencent.tinker.entry.DefaultApplicationLike
//import com.tencent.tinker.loader.shareutil.ShareConstants
//
//
///**
// *Created by liancl on 2020/11/30 0030.
// */
//
//@DefaultLifeCycle(application = ".Appdemo", loaderClass = "com.tencent.tinker.loader.TinkerLoader", flags = ShareConstants.TINKER_ENABLE_ALL, loadVerifyFlag = false)
//class TinkerApplicationLike(application: Application?, tinkerFlags: Int,
//                            tinkerLoadVerifyFlag: Boolean,
//                            applicationStartElapsedTime: Long,
//                            applicationStartMillisTime: Long, tinkerResultIntent: Intent?) : DefaultApplicationLike(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
//        applicationStartMillisTime, tinkerResultIntent) {
//
//
//    override fun onBaseContextAttached(base: Context?) {
//        super.onBaseContextAttached(base)
//        Companion.inst = application
//        //原有的初始化方法
//        MultiDex.install(base)
//        TinkerManager.installTinker(this)
//    }
//
//    companion object{
//        private lateinit var inst :Application;
//        fun getInstance(): Application{
//            return inst
//        }
//    }
//}