package com.example.androidqdemo.ac
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.base.util.ToastUtils
import com.example.androidqdemo.base.util.UiHandler
import com.example.androidqdemo.dl.TipsDialog
import com.example.androidqdemo.manager.tinker.TinkerManager
import com.lodz.android.pandora.base.activity.BaseActivity
import com.lodz.android.pandora.widget.base.LoadingLayout
import com.tencent.tinker.lib.tinker.Tinker
import java.io.File


/**
 * 事件分发
 * Created by liancl on 2020/6/17 0017.
 */
class HotFixActivity : BaseActivity() {

    @JvmField
    @BindView(R.id.tv_jump)
    var tv:TextView?=null;


    var mContext:AppCompatActivity?=null
    override fun getLayoutId(): Int {
        return R.layout.activity_hot_fix
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        ButterKnife.bind(this)
        mContext=this as AppCompatActivity
        goneTitleBar()
        showStatusCompleted()
        val file = File(getExternalFilesDir(null)?.absolutePath + "/patch")
        if (!file.exists()) {
            file.mkdir()
        }
        if (File(getExternalFilesDir(null)?.absolutePath+"/patch"+ File.separator+"patch_signed.apk").exists()) {
            val tipsDialog = TipsDialog(mContext!!)
            tipsDialog.setTitle("检测到有修复包，是否重启修复？")
            tipsDialog.setListener(object :TipsDialog.Listener{
                override fun onClicSure() {
                    tipsDialog.dismiss()
                    UiHandler.post {
                        ToastUtils.show(mContext!!,"修复中")
                    }
                    TinkerManager.loadPatch(getExternalFilesDir(null)?.absolutePath+"/patch"+ File.separator+"patch_signed.apk");
                }

            })
            tipsDialog.show()
        }


    }
    @OnClick(R.id.tv_jump,R.id.tv_new)
    fun  click(v:View ){
        when(v.id){
            R.id.tv_jump->{
//                mContext?.let { HotFix2DetailActivity.start(it) }
                TinkerManager.cleanPatch()
                mContext?.let { ToastUtils.show(it,"清除成功，请重启应用") }
            }
            R.id.tv_new->{
                mContext?.let { HotFixDetailActivity.start(it) }
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


