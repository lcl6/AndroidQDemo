package com.example.pluginapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.pluginapp.R
import com.example.pluginapp.base.PluginBaseActivity
import org.greenrobot.eventbus.EventBus

class PluginSecondActivity : PluginBaseActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plugin_second)
         val tv = findViewById<TextView>(R.id.tv)
         tv.text="我是插件的第2个界面"

         findViewById<TextView>(R.id.tv_finish).setOnClickListener {
             finish()
         }
         findViewById<TextView>(R.id.tv_eventbus).setOnClickListener {
             EventBus.getDefault().post("我是第二个界面发送")
         }


     }
     override fun onDestroy() {
         super.onDestroy()
     }
}
