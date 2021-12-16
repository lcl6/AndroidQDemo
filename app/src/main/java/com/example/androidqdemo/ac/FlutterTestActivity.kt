package com.example.androidqdemo.ac

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androidqdemo.R
import com.example.androidqdemo.view.GoodsView
import io.flutter.embedding.android.FlutterActivity
import java.io.File


/**
 * 自定义view
 * Created by liancl on 2020/6/17 0017.
 */
class FlutterTestActivity : FlutterActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    companion object {
        fun start(context: Context) {
            val starter = Intent(context, FlutterTestActivity::class.java)
            context.startActivity(starter)
        }
    }
}