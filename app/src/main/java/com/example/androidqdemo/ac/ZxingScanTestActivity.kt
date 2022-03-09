package com.example.androidqdemo.ac

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import butterknife.BindView
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.view.jsbridge.BridgeWebView
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.androidqdemo.base.util.ToastUtils
import com.example.androidqdemo.scan.ScanActivity
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.WriterException

import com.google.zxing.BarcodeFormat

import com.google.zxing.common.BitMatrix
import com.hb.dialog.utils.DensityUtils
import com.google.zxing.MultiFormatWriter


/**
 * zxing
 * Created by liancl on 2020/6/17 0017.
 */
class ZxingScanTestActivity : AppCompatActivity() {

    val SCAN_REQUEST_CODE = 100;
    val REQUEST_CODE_SCAN_DEFAULT_MODE = 101;
    @BindView(R.id.tv_blooth_text)
    lateinit var tv: TextView
    lateinit var tvScanRes: TextView
    lateinit var imgQdr: ImageView
    lateinit var imgBar: ImageView
    @SuppressLint("LongLogTag")
    val reContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
        if (it?.resultCode == Activity.RESULT_OK) {
            val data = it.data
            val content = data?.getStringExtra("scan_data");
            Log.i("registerForActivityResult", content.toString())
            tvScanRes.text = content;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_zxing)
        tvScanRes = findViewById<TextView>(R.id.tv_test_res)
        imgQdr = findViewById<ImageView>(R.id.img_qdr)
        imgBar = findViewById<ImageView>(R.id.img_bar)
        findViewById<TextView>(R.id.tv_test_q).setOnClickListener {
            reContent.launch(Intent(this@ZxingScanTestActivity, ScanActivity::class.java));
        }

        findViewById<TextView>(R.id.tv_test_creat_qda).setOnClickListener {
            buildQdrCode()
        }


        findViewById<TextView>(R.id.tv_test_creat_bar).setOnClickListener {
            buildBarCode()
        }
        findViewById<TextView>(R.id.tv_test_test).setOnClickListener {
            startActivity(Intent(this@ZxingScanTestActivity,CameraXTestActivity::class.java))
        }


    }

    private fun buildBarCode() {
        val WHITE = -0x1
        val BLACK = -0x1000000
        val writer = MultiFormatWriter()
        var result: BitMatrix? = null
        try {
            val barcodeFormat = BarcodeFormat.CODE_128
            result = writer.encode("1234567123456", barcodeFormat, 400,
                    100, null)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        val width = result!!.width
        val height = result!!.height
        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (result!![x, y]) BLACK else WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)

        imgBar.setImageBitmap(bitmap)

    }

    private fun buildQdrCode() {

        val width = DensityUtils.dp2px(this, 150F)
        val height = DensityUtils.dp2px(this, 150F)
        val qrCodeWriter = QRCodeWriter()
        val hints = HashMap<EncodeHintType, String>()
        hints[EncodeHintType.CHARACTER_SET] = "utf-8";

        var encode: BitMatrix? = null
        try {
            encode = qrCodeWriter.encode("hello,world!", BarcodeFormat.QR_CODE, width, height, hints)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        val colors = IntArray(width * height)
        //利用for循环将要表示的信息写出来
        for (i in 0 until width) {
            for (j in 0 until height) {
                if (encode!![i, j]) {
                    colors[i * width + j] = Color.BLACK
                } else {
                    colors[i * width + j] = Color.WHITE
                }
            }
        }
        val bit: Bitmap = Bitmap.createBitmap(colors, width, height, Bitmap.Config.RGB_565)
        imgQdr.setImageBitmap(bit)
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, ZxingScanTestActivity::class.java)
//            starter.setFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(starter)
        }
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)

        if (resultCode == SCAN_REQUEST_CODE && resultCode == RESULT_OK) {
        } else if (resultCode == REQUEST_CODE_SCAN_DEFAULT_MODE) {
            Log.i("ZxingScanTestActivity", data.toString());
        }

    }
}


