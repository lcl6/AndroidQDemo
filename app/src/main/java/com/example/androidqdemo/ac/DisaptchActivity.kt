package com.example . androidqdemo.ac
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.EditText
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.util.KeyBoardUtil
import com.huawei.hms.mlsdk.MLAnalyzerFactory
import com.huawei.hms.mlsdk.common.LensEngine
import com.huawei.hms.mlsdk.common.MLAnalyzer
import com.huawei.hms.mlsdk.common.MLAnalyzer.MLTransactor
import com.huawei.hms.mlsdk.common.MLCompositeAnalyzer
import com.huawei.hms.mlsdk.handkeypoint.MLHandKeypointAnalyzerFactory
import com.huawei.hms.mlsdk.handkeypoint.MLHandKeypoints
import com.huawei.hms.mlsdk.imgseg.MLImageSegmentation
import java.io.IOException


/**
 * 事件分发
 * Created by liancl on 2020/6/17 0017.
 */
class DisaptchActivity : AppCompatActivity() {

    @JvmField
    @BindView(R.id.ed_ed)
    var edtv:EditText?=null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dispatch)
        ButterKnife.bind(this)

    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, DisaptchActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(ev?.action==MotionEvent.ACTION_DOWN){
            val v = currentFocus
            edtv?.let { KeyBoardUtil.hideInputMethod(it) };
        }
        Log.e("DisaptchActivity","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev)
//        return false
    }



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //todo  没有到这个方法
        Log.e("DisaptchActivity","onTouchEvent");
        return super.onTouchEvent(event)
    }

    override fun onResume() {
        super.onResume()

        val callback = window.callback;
    }

}


