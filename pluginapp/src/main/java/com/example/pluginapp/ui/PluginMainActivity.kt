package com.example.pluginapp.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.pluginapp.R
import com.example.pluginapp.base.PluginBaseActivity

class PluginMainActivity : PluginBaseActivity() {
    val BROCAST_ACTION:String="com.main.app.demo"
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         initBrocast();
        setContentView(R.layout.activity_plugin_main_normal)
         val tv = findViewById<TextView>(R.id.tv)
         tv.text="我是插件的第一个界面"

         tv.setOnClickListener {
             val intent = Intent(that,PluginSecondActivity::class.java)
             startActivity(intent)
         }
         findViewById<TextView>(R.id.tv_send).setOnClickListener {
            send()
         }

     }
    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(that, "插件收到广播了", Toast.LENGTH_SHORT).show()
        }
    }
    private fun initBrocast() {
        val intentFilter = IntentFilter(BROCAST_ACTION)
        registerReceiver(broadcastReceiver,intentFilter)
    }


    private fun send() {
        val intent = Intent(BROCAST_ACTION)
        sendBroadcast(intent)
    }

    override fun onDestroy() {
        unregisterReceiver(broadcastReceiver)
         super.onDestroy()
     }
}
