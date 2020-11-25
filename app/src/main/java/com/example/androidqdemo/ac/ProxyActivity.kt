package com.example.androidqdemo.ac

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidqdemo.base.util.UiHandler
import com.example.androidqdemo.hook.HookManager
import com.example.pluginiml.ProxyActivityInterface
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.reflect.Constructor


/**
 *Created by liancl on 2020/11/23 0023.
 */
 class ProxyActivity :Activity() {

    var mContext: Activity? =null
    private var pluginObj: ProxyActivityInterface? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        mContext=this
        val className = intent.getStringExtra("ClassName")
        //通过反射在去启动一个真实的activity 拿到Class对象
        try {
            val plugClass = classLoader!!.loadClass(className)
            val pluginConstructor: Constructor<*> = plugClass.getConstructor(*arrayOf<Class<*>>())
            //因为插件的activity实现了我们的标准
            pluginObj = pluginConstructor.newInstance() as ProxyActivityInterface?
            pluginObj?.attach(this) //注入上下文
            pluginObj?.onCreate(Bundle()) //一定要调用onCreate
        } catch (e: Exception) {
            finish()
            Toast.makeText(this, "非法页面", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
     fun notify(event:String){
        Toast.makeText(mContext, "宿主收到eventbus---- $event  满意了吧", Toast.LENGTH_SHORT).show()
    }

//    override fun registerReceiver(receiver: BroadcastReceiver?, filter: IntentFilter?): Intent? {
//
//        return super.registerReceiver(receiver, filter)
//    }
//
//    override fun sendBroadcast(intent: Intent?) {
//        super.sendBroadcast(intent)
//    }
//
//    private val broadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
////            Toast.makeText(context, "宿主收到广播了 满意了吧", Toast.LENGTH_SHORT).show()
//            Log.e("ProxyActivity","宿主收到广播了 满意了吧");
//            UiHandler.postDelayed(Runnable { Toast.makeText(mContext, "宿主收到广播了 满意了吧", Toast.LENGTH_SHORT).show() },1000)
//        }
//    }

    //接收插件传过来的ac 打开
    override fun startActivity(intent: Intent?) {
        val className1 = intent?.getStringExtra("ClassName")
        val intent1 = Intent(this, ProxyActivity::class.java)
        intent1.putExtra("ClassName", className1)
        super.startActivity(intent1)
    }

    override fun getClassLoader(): ClassLoader? {
        return HookManager.instance.getClassLoader()
    }

    override fun getResources(): Resources? {
        return HookManager.instance.getResource()
    }

    override fun onStart() {
        super.onStart()
        pluginObj?.onStart()
    }

    override fun onStop() {
        super.onStop()
        pluginObj?.onStop()
    }

    override fun onResume() {
        super.onResume()
        pluginObj?.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        pluginObj?.onDestroy()
    }



}