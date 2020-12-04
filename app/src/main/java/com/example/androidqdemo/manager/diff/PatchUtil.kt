package com.example.androidqdemo.manager.diff

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.example.androidqdemo.base.util.ToastUtils
import ha.excited.BigNews
import java.io.File


/**
 * 增量更新工具
 *Created by liancl on 2020/12/4 0004.
 */

class PatchUtil {
    companion object{
        fun make(context: Context, outApkPath: String?, patchPath: String?): Boolean {
            if(TextUtils.isEmpty(outApkPath)){
                ToastUtils.show(context,"新包路径为空")
                return false
            }
            if(TextUtils.isEmpty(patchPath)){
                ToastUtils.show(context,"没有找到patch")
                return false
            }
            Log.e("PatchUtil","开始合并");
            return BigNews.make(context.packageResourcePath, outApkPath, patchPath) && File(outApkPath).exists()
        }

        fun diff(context: Context, newApkPath: String?, patchPath: String?): Boolean {
            if(TextUtils.isEmpty(newApkPath)){
                ToastUtils.show(context,"新包路径为空")
                return false
            }
            if(!File(newApkPath).exists()){
                ToastUtils.show(context,"没有找到新包")
                return false
            }
            //旧包的路径（服务器上应该放旧包apk）
            return BigNews.diff(context.packageResourcePath, newApkPath, patchPath) && File(patchPath).exists()
        }

    }
}