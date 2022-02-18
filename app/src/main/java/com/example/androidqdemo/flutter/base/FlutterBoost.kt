package com.example.androidqdemo.flutter.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.androidqdemo.ac.FlutterBaseActivity
import com.example.androidqdemo.flutter.JavaMethodChannel
import com.example.androidqdemo.flutter.route.P
import com.example.pluginiml.BuildConfig
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import java.io.Serializable

/**
 *Created by liancl on 2022/2/18.
 */

class FlutterBoost private constructor(){

    private lateinit var  engineProvider: FlutterEngineProvider


    companion object{
        val instance :FlutterBoost by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            FlutterBoost();
        }
    }

    fun init( application: Application){
        engineProvider = if (BuildConfig.DEBUG) FlutterEngineProviderV2(application) else DefaultFlutterEngineProvider(application)
    }


    fun generateMethodChannel(engineId: String?): JavaMethodChannel {
        val flutterEngine = engineProvider.getFlutterEngine(engineId)!!
        return JavaMethodChannel(flutterEngine.dartExecutor)
    }

    //不带参数
    fun startActivity(context: Context, @P.Route routeName: String?) {
        startActivity(context, routeName, null)
    }

    fun startActivity(context: Context, @P.Route routeName: String?, arguments: Map<String?, Any?>?) {
        context.startActivity(build(context, routeName, arguments))
    }

    fun startActivityForResult(context: Activity, @P.Route routeName: String?, requestCode: Int) {
        startActivityForResult(context, routeName, null, requestCode)
    }

    fun startActivityForResult(fragment: Fragment, @P.Route routeName: String?, requestCode: Int) {
        startActivityForResult(fragment, routeName, null, requestCode)
    }

    fun startActivityForResult(context: Activity, @P.Route routeName: String?, arguments: Map<String?, Any?>?, requestCode: Int) {
        val intent = build(context, routeName, arguments)
        intent.putExtra("ForResult", true)
        context.startActivityForResult(intent, requestCode)
    }

    fun startActivityForResult(context: Fragment, @P.Route routeName: String?, arguments: Map<String?, Any?>?, requestCode: Int) {
        val intent = build(context.context, routeName, arguments)
        intent.putExtra("ForResult", true)
        context.startActivityForResult(intent, requestCode)
    }

    fun build(context: Context?, @P.Route routeName: String?, arguments: Map<String?, Any?>?): Intent {
        val engineId: String = createEngine(context)
        val intent = FlutterActivity.withCachedEngine(engineId) //  .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                .build(context!!)
        intent.setClass(context, FlutterBaseActivity::class.java)
        intent.putExtra("routeName", routeName)
        if (arguments != null) {
            intent.putExtra("arguments", arguments as Serializable?)
        }
        return intent
    }

//    fun <T : FlutterFragment?> fragment(context: Context?, @Route routeName: String?, arguments: Map<String?, Any?>?): T {
//        val engineId: String = FlutterBoost.getInstance().createEngine(context)
//        return MyCachedEngineFragmentBuilder(engineId)
//                .arguments(arguments)
//                .routeName(routeName)
//                .destroyEngineWithFragment(true)
//                .build()
//    }


    /**
     * 重新构建一个 FlutterEngine
     */
    private fun createEngine(context: Context?): String {
        return engineProvider.createAndRunEngine()
    }

    fun remove(engineId: String?) {
        val engineProvider = engineProvider
        engineProvider.remove(engineId)
    }

    private fun _release() {
        val engineProvider = engineProvider
        engineProvider.release()
    }


    fun entryPointName(): String {
        return FlutterEngineProvider.entryPointName()
    }
}