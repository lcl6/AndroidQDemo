package com.example.androidqdemo.ac

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.base.util.ToastUtils
import java.io.File


/**
 * 事件分发
 * Created by liancl on 2020/6/17 0017.
 */
class DdPluginActivity : AppCompatActivity() {

    @JvmField
    @BindView(R.id.tv_jump)
    var tv:TextView?=null;

    var initPlugin=false;

    var mContext:AppCompatActivity?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dd_plugin)
        ButterKnife.bind(this)
        mContext=this as AppCompatActivity
        loadPlugin()


    }

    private fun loadPlugin() {

    }

    @OnClick(R.id.tv_jump)
    fun  click(v:View ){
        if (!initPlugin) {
            ToastUtils.show(this,"插件还未初始化")
            return
        }
        when(v.id){
            R.id.tv_jump->{
            }
        }

    }



    companion object {
        fun start(context: Context) {
            val starter = Intent(context, DdPluginActivity::class.java)
            context.startActivity(starter)
        }
    }



}


