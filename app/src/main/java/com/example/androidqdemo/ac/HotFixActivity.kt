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
import com.example.androidqdemo.base.util.UiHandler
import com.example.androidqdemo.manager.tinker.TinkerManager
import com.tencent.tinker.lib.tinker.Tinker
import java.io.File


/**
 * 事件分发
 * Created by liancl on 2020/6/17 0017.
 */
class HotFixActivity : AppCompatActivity() {

    @JvmField
    @BindView(R.id.tv_jump)
    var tv:TextView?=null;

    var initPlugin=false;

    var mContext:AppCompatActivity?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot_fix)
        ButterKnife.bind(this)
        mContext=this as AppCompatActivity
        Thread(Runnable {
            initPlugin=true
            TinkerManager.loadPatch(getExternalFilesDir(null)?.absolutePath+"/patch"+ File.separator+"patch_signed.apk");
            UiHandler.post {
                ToastUtils.show(mContext!!, Tinker.isTinkerInstalled().toString())
            }


        }).start()
    }
    @OnClick(R.id.tv_jump)
    fun  click(v:View ){
        if (!initPlugin) {
            ToastUtils.show(this,"热修复")
            return
        }
        when(v.id){
            R.id.tv_jump->{

                ToastUtils.show(this,"提示")
            }
        }

    }



    companion object {
        fun start(context: Context) {
            val starter = Intent(context, HotFixActivity::class.java)
            context.startActivity(starter)
        }
    }



}


