package com.example.androidqdemo.ac

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.androidqdemo.flutter.JavaMethodChannel
import com.example.androidqdemo.flutter.base.FlutterBoost
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant
import me.jessyan.autosize.internal.CancelAdapt


/**
 * flutter 主界面
 * Created by liancl on 2020/6/17 0017.
 */
class FlutterBaseActivity : FlutterActivity() ,CancelAdapt{

    private var _channel: JavaMethodChannel? = null
    private var engineId: String? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initChannel()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val intent = intent
        val routeName = intent.getStringExtra("routeName")
        val forResult = intent.getBooleanExtra("ForResult", false)
        val argument = intent.getSerializableExtra("arguments")
        if (routeName != null) {
            _channel?.startRoute(routeName, forResult, argument)
        };
    }


    override fun getCachedEngineId(): String? {
        if (engineId == null) engineId = super.getCachedEngineId()
        return engineId
    }


    override fun getDartEntrypointFunctionName(): String {
        return FlutterBoost.instance.entryPointName();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            _channel?.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        GeneratedPluginRegistrant.registerWith(flutterEngine);

    }
    private fun initChannel() {
        if (_channel != null) return
        _channel = FlutterBoost.instance.generateMethodChannel(cachedEngineId)
        _channel?.attach(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        //请不要删除这个super，会导致所有插件涉及到的权限都会失效
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, FlutterBaseActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _channel?.detach()
        FlutterBoost.instance.remove(cachedEngineId)
    }
}