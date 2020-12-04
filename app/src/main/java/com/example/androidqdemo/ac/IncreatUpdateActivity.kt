package com.example.androidqdemo.ac
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.BuildConfig
import com.example.androidqdemo.R
import com.example.androidqdemo.base.util.ToastUtils
import com.example.androidqdemo.base.util.UiHandler
import com.example.androidqdemo.manager.diff.PatchUtil
import com.example.androidqdemo.manager.tinker.TinkerManager
import com.tencent.tinker.lib.tinker.Tinker
import ha.excited.BigNews.diff
import java.io.File


/**
 * 增量更新
 * Created by liancl on 2020/6/17 0017.
 */
class IncreatUpdateActivity : AppCompatActivity() {

    var  newApkPath:String?=null//修改了内容的apk
    var  outApkPath:String?=null//生成的apk
    var  patchPath:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_increat_update)
        ButterKnife.bind(this)
        //创建文件路径
        val file = File(getExternalFilesDir(null)?.absolutePath + "/increatupdate")
        if (!file.exists()) {
            file.mkdir()
        }
        newApkPath=file.absolutePath+"/newApk.apk"//修改内容的包
        outApkPath=file.absolutePath+"/outApk.apk"//生成的新包
        patchPath=file.absolutePath+"/patch"
    }

    @OnClick(R.id.tv_creat_diff,R.id.tv_updte,R.id.install)
    public fun onClick(view: View){
        when (view.id) {
            R.id.tv_creat_diff -> {
                val diff = PatchUtil.diff(this, newApkPath, patchPath)
                UiHandler.post {
                    ToastUtils.show(this, "生成差分包$diff")
                }

            }
            R.id.tv_updte -> {
                val make = PatchUtil.make(this, outApkPath, patchPath)
                Log.e("PatchUtil", "合并完成 ====$make");
                UiHandler.post {
                    ToastUtils.show(this, "合成$make")
                }

            }
            R.id.install -> {
                install()

            }
            else -> {
            }
        }
    }

    private fun install() {
        if(TextUtils.isEmpty(outApkPath)){
            ToastUtils.show(this,"输出包路径为空")
            return
        }
        if(!File(outApkPath).exists()){
            ToastUtils.show(this,"输出包文件为空")
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val uriForFile = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", File(outApkPath))
            val dataAndType = Intent(Intent.ACTION_VIEW)
                    .setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    .setDataAndType(uriForFile, "application/vnd.android.package-archive")
            startActivity(dataAndType);
        } else {
            startActivity(Intent(Intent.ACTION_VIEW).setDataAndType(Uri.fromFile( File(outApkPath)), "application/vnd.android.package-archive"));
        }
    }


    companion object {
        fun start(context: Context) {
            val starter = Intent(context, IncreatUpdateActivity::class.java)
            context.startActivity(starter)
        }
    }



}


