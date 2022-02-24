package com.example.androidqdemo.ac
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebSettings
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import butterknife.BindView
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.view.jsbridge.BridgeWebView
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.androidqdemo.base.util.ToastUtils
import com.example.androidqdemo.scan.ScanActivity


/**
 * zxing
 * Created by liancl on 2020/6/17 0017.
 */
class ZxingScanTestActivity : AppCompatActivity() {

    val SCAN_REQUEST_CODE =100;

    @BindView(R.id.tv_blooth_text)
    lateinit var tv: TextView


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_zxing)


        findViewById<TextView>(R.id.tv_test_q).setOnClickListener {
            val intent: Intent = Intent(this@ZxingScanTestActivity, ScanActivity::class.java)
            startActivityForResult(intent, SCAN_REQUEST_CODE)
        }

    }
    companion object {
        fun start(context: Context) {
            val starter = Intent(context, ZxingScanTestActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)

        if (resultCode == SCAN_REQUEST_CODE && resultCode == RESULT_OK) {
//            val stringExtra = intent.getStringExtra(ScanActivity.INTENT_EXTRA_RESULT)
//            ToastUtils.show(this@ZxingScanTestActivity, "扫描结果:$stringExtra");
        }

    }
}


