package com.example.androidqdemo.base.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager

/**
 * 获取网络连接的工具类
 * Created by laihm on 2016/11/17.
 */
object PhoneNetUtil {
    //没有网络连接
    const val NETWORN_NONE = "none"
    //wifi连接
    const val NETWORN_WIFI = "wifi"
    //手机网络数据连接类型
    const val NETWORN_2G = "2G"
    const val NETWORN_3G = "3G"
    const val NETWORN_4G = "4G"
    const val NETWORN_MOBILE = "mobile"
    /**
     * 获取当前网络连接类型
     * @param context
     * @return
     */
    fun getNetworkState(context: Context): String { //获取系统的网络服务
        val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                ?: return NETWORN_NONE
        //如果当前没有网络
        //获取当前网络类型，如果为空，返回无网络
        val activeNetInfo = connManager.activeNetworkInfo
        if (activeNetInfo == null || !activeNetInfo.isAvailable) {
            return NETWORN_NONE
        }
        // 判断是不是连接的是不是wifi
        val wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (null != wifiInfo) {
            val state = wifiInfo.state
            if (null != state) if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                return NETWORN_WIFI
            }
        }
        // 如果不是wifi，则判断当前连接的是运营商的哪种网络2g、3g、4g等
        val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (null != networkInfo) {
            val state = networkInfo.state
            val strSubTypeName = networkInfo.subtypeName
            if (null != state) if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                return when (activeNetInfo.subtype) {
                    TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> NETWORN_2G
                    TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> NETWORN_3G
                    TelephonyManager.NETWORK_TYPE_LTE -> NETWORN_4G
                    else ->  //中国移动 联通 电信 三种3G制式
                        if (strSubTypeName.equals("TD-SCDMA", ignoreCase = true) || strSubTypeName.equals("WCDMA", ignoreCase = true) || strSubTypeName.equals("CDMA2000", ignoreCase = true)) {
                            NETWORN_3G
                        } else {
                            NETWORN_MOBILE
                        }
                }
            }
        }
        return NETWORN_NONE
    }

    /**
     * 检查当前手机网络是否可用
     * @return
     */
    fun isConnect(context: Context): Boolean {
        try { //获取系统链接管理对象
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (manager != null) { //获取网络信息管理对象
                val info = manager.activeNetworkInfo
                if (info != null && info.isConnected) { //判断当前网络是否已链接
                    if (info.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        } catch (e: Exception) { // TODO: handle exception
        }
        return false
    }

    /**
     * 检查当前网络是否为wifi网络
     * @param mContext
     * @return
     */
    fun isWifi(mContext: Context): Boolean {
        val connectivityManager = mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI
    }

    fun getFFormatVer(appVersion: String): String {
        var version = "0.0.0"
        val verlist = appVersion.split("\\.").toTypedArray()
        val s1 = if (Integer.valueOf(verlist[0]) > 10) verlist[0] else verlist[0].substring(1)
        val s2 = if (Integer.valueOf(verlist[1]) > 10) verlist[1] else verlist[1].substring(1)
        val s3 = if (Integer.valueOf(verlist[2]) > 10) verlist[2] else verlist[2].substring(1)
        version = "$s1.$s2.$s3"
        return version
    }

    /**
     * 格式化版本
     */
    fun getFormatVer(appVersion: String): String {
        var version = "00.00.00"
        val verlist = appVersion.split("\\.").toTypedArray()
        val s1 = if (Integer.valueOf(verlist[0]) > 10) verlist[0] else "0" + verlist[0]
        val s2 = if (Integer.valueOf(verlist[1]) > 10) verlist[1] else "0" + verlist[1]
        val s3 = if (Integer.valueOf(verlist[2]) > 10) verlist[2] else "0" + verlist[2]
        version = "$s1.$s2.$s3"
        return version
    }
}