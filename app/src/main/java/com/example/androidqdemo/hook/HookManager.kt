package com.example.androidqdemo.hook

import android.app.Activity
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dalvik.system.DexClassLoader
import java.io.*
import java.lang.reflect.Method
import kotlin.math.log

/**
 *Created by liancl on 2020/11/23 0023.
 */

open class HookManager private constructor(){
    private lateinit var loader: DexClassLoader
    private lateinit var resources: Resources
    private var packageInfo : PackageInfo? =null
    companion object {

        val instance: HookManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HookManager()
        }
    }

        //用来加载插件
        open fun loadPlugin(activity: AppCompatActivity) {
//            // 假如这里是从网络获取的插件 我们直接从sd卡获取 然后读取到我们的cache目录
//            val pluginName = "plugin.apk"
//            val filePath = activity.externalCacheDir?.absolutePath + "/" + pluginName
//            Log.e("loadPlugin",filePath+"")
////            val file = File(filePath)
////            if (file.exists()) {
////                file.delete()
////            }
//            var `is`: FileInputStream? = null
//            var os: FileOutputStream? = null
//            //读取的目录
//            try {
//                `is` = FileInputStream(File(filePath))
//                //要输入的目录
//                os = FileOutputStream(filePath)
//            } catch (e: FileNotFoundException) {
//                e.printStackTrace()
//            }
//            try {
//                var len = 0
//                val buffer = ByteArray(1024)
//                while (`is`?.read(buffer).also { len = it!! } != -1) {
//                    os?.write(buffer, 0, len)
//                }
//                val f = File(filePath)
//                if (f.exists()) {
//                    Toast.makeText(activity, "dex overwrite", Toast.LENGTH_SHORT).show()
//                }
//                loadPathToPlugin(activity)
//            } catch (e: IOException) {
//                e.printStackTrace()
//            } finally {
//                try {
//                    os?.close()
//                    `is`?.close()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//            }

            loadPathToPlugin(activity)
        }

        private fun loadPathToPlugin(activity: Activity) {
            val pluginName = "plugin.apk"
            val filePath = activity.externalCacheDir?.absolutePath + "/" + pluginName

            val path: String =filePath

            //然后我们开始加载我们的apk 使用DexClassLoader
//            val dexOutDir: File = activity.getDir("dex", Context.MODE_PRIVATE)
            loader = DexClassLoader(path, filePath, null, activity.classLoader)

            //通过PackAgemanager 来获取插件的第一个activity是哪一个
            val packageManager: PackageManager = activity.packageManager
            packageInfo= packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES)

            //然后开始加载我们的资源 肯定要使用Resource 但是它是AssetManager创建出来的 就是AssertManager 有一个addAssertPath 这个方法 但是私有的 所有使用反射
            val assetManagerClass: Class<*> = AssetManager::class.java
            try {
                val assertManagerObj: AssetManager = assetManagerClass.newInstance() as AssetManager
                val addAssetPathMethod: Method = assetManagerClass.getMethod("addAssetPath", String::class.java)
                addAssetPathMethod.isAccessible = true
                addAssetPathMethod.invoke(assertManagerObj, path)
                //在创建一个Resource
                resources = Resources(assertManagerObj, activity.resources.displayMetrics, activity.resources.configuration)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        //对外提供插件的classLoader
        open fun getClassLoader(): ClassLoader? {
            return loader
        }

        //插件中的Resource
        open fun getResource(): Resources? {
            return resources
        }

        fun getPageinfo():PackageInfo?{
            return packageInfo
        }


}