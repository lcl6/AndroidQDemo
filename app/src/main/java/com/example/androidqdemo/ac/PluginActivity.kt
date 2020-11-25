package com.example.androidqdemo.ac
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.base.util.ToastUtils
import com.example.androidqdemo.hook.HookManager


/**
 * 事件分发
 * Created by liancl on 2020/6/17 0017.
 */
class PluginActivity : AppCompatActivity() {

    @JvmField
    @BindView(R.id.tv_jump)
    var tv:TextView?=null;

    var initPlugin=false;

    var mContext:AppCompatActivity?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plugin)
        ButterKnife.bind(this)
        mContext=this as AppCompatActivity
        Thread(Runnable {
            initPlugin=true
            HookManager.instance.loadPlugin(mContext!!);
            Log.e("PluginActivity","插件加载完成");
        }).start()
    }
    @OnClick(R.id.tv_jump)
    fun  click(v:View ){
        if (!initPlugin) {
            ToastUtils.show(this,"插件还未初始化")
            return
        }
        when(v.id){
            R.id.tv_jump->{
                val intent = Intent(this, ProxyActivity::class.java) //这里就是一个占坑的activity
                //这里是拿到我们加载的插件的第一个activity的全类名
                intent.putExtra("ClassName", HookManager.instance.getPageinfo()?.activities?.get(0)?.name)
                startActivity(intent)
            }
        }

    }



    companion object {
        fun start(context: Context) {
            val starter = Intent(context, PluginActivity::class.java)
            context.startActivity(starter)
        }
    }



}


