package com.example.androidqdemo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidqdemo.ac.MainActivity
import com.example.androidqdemo.ac.MainTabActivity
import com.example.androidqdemo.base.util.AppUtils.isPermissionGranted
import com.example.androidqdemo.base.util.ToastUtils.show
import permissions.dispatcher.*

@RuntimePermissions

class WelComeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // 6.0以上的手机对权限进行动态申请
            needPermissionWithPermissionCheck()
        } else {
            init()
        }
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
    fun needPermission() {
        if (!isPermissionGranted(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return
        } else {
            if (!isPermissionGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                return
            }
            init()
        }
    }

    private fun init() {
        val intent = Intent(this, MainTabActivity::class.java)
        startActivity(intent)
        finish()
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        onRequestPermissionsResult( requestCode, grantResults)
    }

    @OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
    fun showRational(request: PermissionRequest) {
        request.proceed()
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
    fun permisiondenied() {
        show(this@WelComeActivity, "已拒绝")
    }

    @OnNeverAskAgain(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
    fun neverAsk() {
        show(this@WelComeActivity, "不再询问".toInt().toString())
        finish()
    }

    override fun onDestroy() {
        Log.e("onDestroy", "onDestroy")
        super.onDestroy()
    }
}