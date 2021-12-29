package com.example.androidqdemo.ac
import android.annotation.SuppressLint
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
import androidx.appcompat.app.AppCompatActivity
import com.example.androidqdemo.R
import com.example.androidqdemo.view.jsbridge.BridgeWebView


/**
 * 事件分发
 * Created by liancl on 2020/6/17 0017.
 */
class VideoActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val window: Window = window
        val decorView: View = window.decorView
        //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
        val option: Int = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        decorView.systemUiVisibility = option
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT

        setContentView(R.layout.activity_video)



        val webview = findViewById<BridgeWebView>(R.id.web_view)
        val webSettings: WebSettings = webview.settings
        //支持缩放
        webSettings.setSupportZoom(true)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        //自适应屏幕
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.domStorageEnabled = true
        //允许js 并读取文件
        webSettings.javaScriptEnabled = true

        webSettings.allowFileAccess = true //重要
        webSettings.allowFileAccessFromFileURLs = true
        webSettings.allowUniversalAccessFromFileURLs = true

        webview.registerHandler("submitFromWeb") { data, function ->
            Log.e("VideoActivity", "web 发送过来的数据：$data\n")
            function?.onCallBack("java get param")
            //判断当前屏幕方向
            requestedOrientation = if(requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                //切换竖屏
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
            }else{
                //切换横屏
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            }
        }

        webview.setDefaultHandler { data, function ->
            Log.e("VideoActivity", "web 发送过来的数据：$data\n")
            function.onCallBack("java get user info")
        }

        var url : String ="http://2449.vod.myqcloud.com/2449_43b6f696980311e59ed467f22794e792.f20.mp4";
        //videofull 不能自动播放
        webview.loadUrl("file:///android_asset/video/videofull.html?path=$url")
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, VideoActivity::class.java)
            context.startActivity(starter)
        }
    }




}


