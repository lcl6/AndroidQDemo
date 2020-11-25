package com.example.androidqdemo.hook

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import com.example.androidqdemo.R
import dalvik.system.DexClassLoader
import java.io.File
import java.lang.reflect.Method

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
//            parseTheme(activity,path)
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
    /**
     * 通过解析清单文件 设置主题
     *
     * @param activity
     * @param path
     */
    open fun parseTheme(activity: Activity, path: String) {
        try {
            //我们知道解析一个apk文件的入口就是PackageParse.parsePackage 这个方法
            //所以我们使用反射 来调用这个方法 最终得到了一个 PackageParse$Package 这个类
            val mPackageParseClass = Class.forName("android.content.pm.PackageParser")
            val mParsePackageMethod = mPackageParseClass.getDeclaredMethod("parsePackage", File::class.java, Int::class.javaPrimitiveType)
            val mPackageParseObj = mPackageParseClass.newInstance()
            val mPackageObj = mParsePackageMethod.invoke(mPackageParseObj, File(path), PackageManager.GET_ACTIVITIES)

            //解析出来的activity就存在PackageParse$Package 这个类里面的一个activity集合里面
            val activityListFied = mPackageObj.javaClass.getDeclaredField("activity")
            //然后得到反射得到这个属性的值 最终得到一个集合
            val mActivityList = activityListFied[mPackageObj] as List<*>
            // 拿到generateActivityInfo这个方法
            //这两行是为了调用generateActivityInfo 而反射拿到的参数
            val `mPackageParse$ActivityClass` = Class.forName("android.content.pm.PackageParser\$Activity")
            val mPackageUserStateClass = Class.forName("android.content.pm.PackageUserState")
            val mPackzgeUserStateObj = mPackageUserStateClass.newInstance()


            // 拿到generateActivityInfo这个方法
            val mGeneReceiverInfo = mPackageParseClass.getMethod("generateActivityInfo", `mPackageParse$ActivityClass`, Int::class.javaPrimitiveType, mPackageUserStateClass, Int::class.javaPrimitiveType)
            val mUserHandlerClass = Class.forName("android.os.UserHandle")
            val getCallingUserIdMethod = mUserHandlerClass.getDeclaredMethod("getCallingUserId")
            val userId = getCallingUserIdMethod.invoke(null) as Int

            for (activityObj in mActivityList) {
                // 这个是我们要调用的方法的形参 public static final ActivityInfo generateActivityInfo(Activity a, int flags,PackageUserState state, int userId);
                //得到一个ActivityInfo
                val info = mGeneReceiverInfo.invoke(mPackageParseObj, activityObj, 0, mPackzgeUserStateObj, userId) as ActivityInfo
                //拿到这个name 相当于我们在清单文件中Android:name 这样，是一个全类名，然后通过反射去创建 对象
                val appCompatActivity = getClassLoader()!!.loadClass(info.name).newInstance() as AppCompatActivity
                appCompatActivity.setTheme(R.style.AppTheme)

            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 通过解析清单文件来 拿到静态广播并且进行注册
     *
     * @param activity
     * @param path
     */
     open fun parseReceivers(activity: Activity, path: String) {
        try {
            //我们知道解析一个apk文件的入口就是PackageParse.parsePackage 这个方法
            //所以我们使用反射 来调用这个方法 最终得到了一个 PackageParse$Package 这个类
            val mPackageParseClass = Class.forName("android.content.pm.PackageParser")
            val mParsePackageMethod = mPackageParseClass.getDeclaredMethod("parsePackage", File::class.java, Int::class.javaPrimitiveType)
            val mPackageParseObj = mPackageParseClass.newInstance()
            val mPackageObj = mParsePackageMethod.invoke(mPackageParseObj, File(path), PackageManager.GET_ACTIVITIES)

            //解析出来的receiver就存在PackageParse$Package 这个类里面的一个receivers集合里面
            val mReceiversListField = mPackageObj.javaClass.getDeclaredField("receivers")
            //然后得到反射得到这个属性的值 最终得到一个集合
            val mReceiverList = mReceiversListField[mPackageObj] as List<*>

            //接下来我们要拿到 IntentFilter 和name属性 这样才能反射创建对象，动态在宿主里面注册广播
            val mComponetClass = Class.forName("android.content.pm.PackageParser\$Component")
            val mIntentFields = mComponetClass.getDeclaredField("intents")

            //这两行是为了调用generateActivityInfo 而反射拿到的参数
            val `mPackageParse$ActivityClass` = Class.forName("android.content.pm.PackageParser\$Activity")
            val mPackageUserStateClass = Class.forName("android.content.pm.PackageUserState")
            val mPackzgeUserStateObj = mPackageUserStateClass.newInstance()


            // 拿到generateActivityInfo这个方法
            val mGeneReceiverInfo = mPackageParseClass.getMethod("generateActivityInfo", `mPackageParse$ActivityClass`, Int::class.javaPrimitiveType, mPackageUserStateClass, Int::class.javaPrimitiveType)
            val mUserHandlerClass = Class.forName("android.os.UserHandle")
            val getCallingUserIdMethod = mUserHandlerClass.getDeclaredMethod("getCallingUserId")
            val userId = getCallingUserIdMethod.invoke(null) as Int


            //然后for循环 去拿到name和 intentFilter
            for (activityObj in mReceiverList) {
                //调用generateActivityInfo
                // 这个是我们要调用的方法的形参 public static final ActivityInfo generateActivityInfo(Activity a, int flags,PackageUserState state, int userId);
                //得到一个ActivityInfo
                val info = mGeneReceiverInfo.invoke(mPackageParseObj, activityObj, 0, mPackzgeUserStateObj, userId) as ActivityInfo
                //拿到这个name 相当于我们在清单文件中Android:name 这样，是一个全类名，然后通过反射去创建 对象
                val broadcastReceiver = getClassLoader()!!.loadClass(info.name).newInstance() as BroadcastReceiver

                //在拿到IntentFilter
                val intents = mIntentFields[activityObj] as List<IntentFilter>
                //然后直接调用registerReceiver方法发
                for (intentFilter in intents) {
                    activity.registerReceiver(broadcastReceiver, intentFilter)
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }



}