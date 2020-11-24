package com.example.pluginapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.pluginapp.R
import com.example.pluginapp.base.PluginBaseActivity

 class PluginMainActivity : PluginBaseActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plugin_main_normal)
         val tv = findViewById<TextView>(R.id.tv)
         tv.text="我是插件的第一个界面"

     }

     override fun onDestroy() {
//         Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show()
         super.onDestroy()
     }
}
