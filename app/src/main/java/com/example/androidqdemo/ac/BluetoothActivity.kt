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
import butterknife.BindView
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.view.jsbridge.BridgeWebView


/**
 * 事件分发
 * Created by liancl on 2020/6/17 0017.
 */
class BluetoothActivity : AppCompatActivity() {


    @BindView(R.id.tv_blooth_text)
    lateinit var tv: TextView
    lateinit  var bluetoothManager:BluetoothManager;
    lateinit  var mBluetoothAdapter:BluetoothAdapter;


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fg_bloue_tooth)

        bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager;
        mBluetoothAdapter = bluetoothManager.adapter;

        if (!mBluetoothAdapter.isEnabled) {
            var enableBtIntent =  Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE) as Intent
            startActivityForResult(enableBtIntent, 1);
        }

    }
    companion object {
        fun start(context: Context) {
            val starter = Intent(context, BluetoothActivity::class.java)
            context.startActivity(starter)
        }
    }


    private fun toSearch() {
        if (mBluetoothAdapter.isDiscovering) {
            mBluetoothAdapter.bluetoothLeScanner.stopScan(object : ScanCallback(){
                override fun onScanResult(callbackType: Int, result: ScanResult?) {
                    super.onScanResult(callbackType, result)
                    Log.e("result",result.toString());
                }
            })
        }

        mBluetoothAdapter.bluetoothLeScanner.startScan(object : ScanCallback(){
            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                super.onScanResult(callbackType, result)
                Log.e("result",result.toString());
            }
        })

//        if (mBluetoothAdapter.isDiscovering) {
//            mBluetoothAdapter.stopLeScan(mLeScanCallback);
//        }
//        mBluetoothAdapter.startLeScan(mLeScanCallback);
    }
    @OnClick(R.id.tv_test_q)
    public fun onClick(v:View){
        if(v.id==R.id.tv_test_q){
            toSearch()
        }


    }

}


