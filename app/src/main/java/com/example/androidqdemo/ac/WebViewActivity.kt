package com.example.androidqdemo.ac

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androidqdemo.R
import java.io.File


/**
 * 沉浸状态栏
 * Created by liancl on 2020/6/17 0017.
 */
class WebViewActivity : AppCompatActivity() {
    @JvmField
    @BindView(R.id.webview)
    var webView: WebView? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        ButterKnife.bind(this)

        val settings = webView?.settings;
        settings?.allowUniversalAccessFromFileURLs = true;
        settings?.allowFileAccess = true;
        settings?.allowFileAccessFromFileURLs = true;

        val path = "file://" + Environment.getExternalStorageDirectory() + File.separator.toString() + "123.jpg"
        //用于js调用Android
        settings?.javaScriptEnabled = true
        //设置编码方式
        //设置编码方式
        settings?.defaultTextEncodingName = "utf-8"
        //访问Android assets文件夹内的
        val url = "file:///android_asset/test.html"
        runWebView(url);
        webView!!.webChromeClient =object :WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    //页面加载完成执行的操作
                    val path = "file://" + Environment.getExternalStorageDirectory() + File.separator + "123.jpg"
                    val action = "javascript:aa('$path')"
                    runWebView(action);
                }

            }



        }
    }

    public fun runWebView(url: String) {
        runOnUiThread { webView!!.loadUrl(url) }
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, WebViewActivity::class.java)
            context.startActivity(starter)
        }
    }
}