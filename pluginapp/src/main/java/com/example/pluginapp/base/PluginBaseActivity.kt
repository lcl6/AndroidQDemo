package com.example.pluginapp.base

import android.app.Activity
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.view.*
import com.example.pluginiml.ProxyActivityInterface

open class  PluginBaseActivity : Activity() , ProxyActivityInterface {

     var that: Activity?=null
    override fun attach(activity: Activity) {
        that=activity
    }




     override fun setContentView(view: View?) {
         if (that != null) {
             that!!.setContentView(view);
         } else {
             super.setContentView(view);
         }
     }

     override fun setContentView(layoutResID: Int) {
         that?.setContentView(layoutResID)
     }

    override fun <T : View?> findViewById(id: Int): T {
        return that!!.findViewById<T>(id)
    }

     override fun getIntent(): Intent {
         if(that!=null){
             return  that!!.intent
         }
         return super.getIntent()
     }

     override fun getClassLoader(): ClassLoader {
          return  that!!.classLoader
     }

     override fun getLayoutInflater(): LayoutInflater {
         return  that!!.layoutInflater

     }


     override fun getApplicationInfo(): ApplicationInfo {
         return  that!!.applicationInfo
     }

     override fun getWindow(): Window {
         return  that!!.window
     }

     override fun getWindowManager(): WindowManager {
         return that!!.windowManager
     }

     override fun startActivity(intent: Intent?) {
         val intent1 = Intent()
         intent1.putExtra("ClassName", intent?.component?.className)
         that?.startActivity(intent)
     }




    override fun onCreate(savedInstanceState: Bundle?) {

    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }


    override fun onStop() {
    }

    override fun onDestroy() {
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return true
    }

    override fun onBackPressed() {
    }

 }
