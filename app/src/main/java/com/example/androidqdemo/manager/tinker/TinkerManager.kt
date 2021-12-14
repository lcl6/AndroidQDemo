//package com.example.androidqdemo.manager.tinker
//
//import android.content.Context
//import com.example.androidqdemo.base.util.ToastUtils
//import com.tencent.tinker.entry.ApplicationLike
//import com.tencent.tinker.lib.tinker.Tinker
//import com.tencent.tinker.lib.tinker.TinkerInstaller
//
//
///**
// *Created by liancl on 2020/11/30 0030.
// */
//
//class TinkerManager private constructor() {
//
//    companion object {
//        val instance: TinkerManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
//            TinkerManager()
//        }
//
//
//        //标记是否安装过Tinker
//        private var isInstalled = false
//
//        private var mAppLike: ApplicationLike? = null
//
//
//        /**
//         * 完成tinker的初始化
//         * @param applicationLike
//         */
//        fun installTinker(applicationLike: TinkerApplicationLike?) {
//            mAppLike = applicationLike
//            if (isInstalled) {
//                return
//            }
//
//            //完成Tinker的初始化
//            TinkerInstaller.install(mAppLike)
//            isInstalled = true
//        }
//
//        /**
//         * 完成patch文件的加载
//         * @param path
//         */
//        fun loadPatch(path: String?) {
//            if (Tinker.isTinkerInstalled()) {
//                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path)
//            }
//        }
//
//        /**
//         * 清除补丁
//         */
//        fun cleanPatch(){
//            if (Tinker.isTinkerInstalled()) {
//                TinkerInstaller.cleanPatch(getApplicationContext())
//            }
//        }
//
//        private fun getApplicationContext(): Context? {
//            return if (mAppLike != null) {
//                mAppLike!!.application.applicationContext
//            } else null
//        }
//
//
//    }
//}