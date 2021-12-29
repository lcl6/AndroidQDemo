package com.example.androidqdemo.base.util

import android.Manifest
import android.app.ActivityManager
import android.app.admin.DevicePolicyManager
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Looper
import android.os.Process
import android.provider.Settings
import android.text.TextUtils
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import java.util.*

/**
 * APP帮助类
 * Created by zhouL on 2016/11/17.
 */
object AppUtils {
    /**
     * 获取应用程序名称
     * @param context 上下文
     */
    fun getAppName(context: Context): String {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            val labelRes = packageInfo.applicationInfo.labelRes
            return context.resources.getString(labelRes)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 获取客户端版本名称
     * @param context 上下文
     */
    fun getVersionName(context: Context): String {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 获取客户端版本号
     * @param context 上下文
     */
    fun getVersionCode(context: Context): Int {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionCode
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return -1
    }

    /** 当前是否在主线程（UI线程）  */
    val isMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()

    /**
     * 判断传入的context是不是在主进程
     * @param context 上下文
     */
    fun isMainProcess(context: Context): Boolean {
        val processName = getProcessName(context)
        return !TextUtils.isEmpty(processName) && !processName.contains(":")
    }

    /**
     * 获取进程名称
     * @param context 上下文
     */
    fun getProcessName(context: Context): String {
        val pid = Process.myPid()
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                ?: return ""
        val runningApps = am.runningAppProcesses ?: return ""
        for (procInfo in runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName
            }
        }
        return ""
    }
    //    /**
//     * 安装akp文件
//     * @param context 上下文
//     * @param apkPath apk路径
//     */
//    public static void installApk(Context context, @NonNull String apkPath, String authority) throws Exception {
//        File file = FileUtils.createFile(apkPath);
//        if (!FileUtils.isFileExists(file)){
//            throw new IllegalArgumentException("file no exists");
//        }
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Uri apkUri = FileProvider.getUriForFile(context, authority, file);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
//        }else {
//            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//        }
//        context.startActivity(intent);
//    }
    /**
     * 卸载应用
     * @param context 上下文
     * @param packageName 包名
     */
    fun uninstallApp(context: Context, packageName: String) {
        val intent = Intent(Intent.ACTION_DELETE, Uri.parse("package:$packageName"))
        context.startActivity(intent)
    }

    /** 获取随机的UUID  */
    val uUID: String
        get() = UUID.randomUUID().toString()

    /**
     * 通过LaunchIntent打开APP
     * @param context 上下文
     * @param packageName 目标APP的包名
     */
    @Throws(IllegalArgumentException::class, ActivityNotFoundException::class)
    fun openAppByLaunch(context: Context?, packageName: String?) {
        require(!(context == null || TextUtils.isEmpty(packageName))) { "参数不能为空" }
        val intent = context.packageManager.getLaunchIntentForPackage(packageName!!)
                ?: throw ActivityNotFoundException()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    /**
     * 通过android.intent.action.MAIN来打开APP
     * @param context 上下文
     * @param packageName 目标APP的包名
     */
    @Throws(IllegalArgumentException::class, ActivityNotFoundException::class)
    fun openAppByActionMain(context: Context?, packageName: String) {
        require(!(context == null || TextUtils.isEmpty(packageName))) { "参数不能为空" }
        var mainActivityName = "" // 启动页的路径
        val intent = Intent(Intent.ACTION_MAIN)
        val packageManager = context.packageManager
        for (resolve in packageManager.queryIntentActivities(intent, 0)) {
            val info = resolve.activityInfo ?: continue
            if (packageName == info.packageName) {
                mainActivityName = info.name
                break
            }
        }
        if (TextUtils.isEmpty(mainActivityName)) {
            throw ActivityNotFoundException("没有找到该应用")
        }
        intent.component = ComponentName(packageName, mainActivityName)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    /**
     * 判断对应包名的app是否安装
     * @param context 上下文
     * @param pkgName 包名
     */
    fun isPkgInstalled(context: Context?, pkgName: String?): Boolean {
        if (context == null || TextUtils.isEmpty(pkgName)) {
            return false
        }
        try {
            val packageInfo = context.packageManager.getPackageInfo(pkgName!!, PackageManager.GET_ACTIVITIES or PackageManager.GET_SERVICES)
            return packageInfo != null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 获取已安装的PackageInfo列表
     * @param context 上下文
     */
    fun getInstalledPackages(context: Context?): List<PackageInfo> {
        if (context == null) {
            return ArrayList()
        }
        val packageInfos = context.packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES or PackageManager.GET_SERVICES)
        return packageInfos ?: ArrayList()
    }

    /**
     * 获取对应包名的PackageInfo
     * @param context 上下文
     * @param pkgName 包名
     */
    fun getPackageInfo(context: Context?, pkgName: String?): PackageInfo? {
        if (context == null || TextUtils.isEmpty(pkgName)) {
            return null
        }
        try {
            return context.packageManager.getPackageInfo(pkgName!!, PackageManager.GET_ACTIVITIES or PackageManager.GET_SERVICES)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 权限是否被授予
     * @param context 上下文
     * @param permission 权限
     */
    fun isPermissionGranted(context: Context?, permission: String?): Boolean {
        try {
            return ActivityCompat.checkSelfPermission(context!!, permission!!) == PackageManager.PERMISSION_GRANTED
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 根据包名打开对应的设置界面
     * @param context 上下文
     */
    fun jumpAppDetailSetting(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:" + context.packageName)
        context.startActivity(intent)
    }

    /**
     * 跳转到日期设置页面
     * @param context 上下文
     */
    fun jumpDateSetting(context: Context) {
        val intent = Intent(Settings.ACTION_DATE_SETTINGS)
        context.startActivity(intent)
    }

    /**
     * 获取MetaData
     * @param context 上下文
     * @param key 标签名
     */
    fun getMetaData(context: Context, key: String?): Any? {
        try {
            val appInfo = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            return appInfo.metaData[key]
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 跳转到定位设置页
     * @param context 上下文
     */
    fun jumpLocationSetting(context: Context) {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        context.startActivity(intent)
    }

    /**
     * GPS是否打开
     * @param context 上下文
     */
    fun isGpsOpen(context: Context): Boolean {
        val str = Settings.Secure.getString(context.contentResolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED)
        return !TextUtils.isEmpty(str) && (str.contains("gps") || str.contains("GPS"))
    }

    /**
     * wifi是否可用
     * @param context 上下文
     */
    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    fun isWifiEnabled(context: Context): Boolean {
        val manager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return manager != null && manager.isWifiEnabled
    }

    /**
     * 设置wifi是否可用
     * @param context 上下文
     * @param enabled 是否可用
     */
    @RequiresPermission(Manifest.permission.CHANGE_WIFI_STATE)
    fun setWifiEnabled(context: Context, enabled: Boolean) {
        val manager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (manager != null && manager.isWifiEnabled) {
            manager.isWifiEnabled = enabled
        }
    }

    /**
     * 跳转到WIFI设置页
     * @param context 上下文
     */
    fun jumpWifiSetting(context: Context) {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        context.startActivity(intent)
    }

    /**
     * 跳转到数据流量设置页
     * @param context 上下文
     */
    fun jumpDataRoamingSetting(context: Context) {
        val intent = Intent(Settings.ACTION_DATA_ROAMING_SETTINGS)
        context.startActivity(intent)
    }

    /**
     * 跳转到密码设置页
     * @param context 上下文
     */
    fun jumpSetPswdSetting(context: Context) {
        val intent = Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD)
        context.startActivity(intent)
    }
}