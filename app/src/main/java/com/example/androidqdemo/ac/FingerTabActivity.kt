package com.example.androidqdemo.ac

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
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
 * 沉浸状态栏
 * Created by liancl on 2020/6/17 0017.
 */
class FingerTabActivity : AppCompatActivity() {

    @JvmField
    @BindView(R.id.sw)
    var  mSurfaceView: SurfaceView? =null
    @JvmField
    @BindView(R.id.tv_receive)
    var tvReceive :TextView?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fingertab)
        ButterKnife.bind(this)
        initSS()

        regist()

    }

    private fun regist() {

        val loginIntentFilter = IntentFilter()
        loginIntentFilter.addAction("com.ydjw.ua.ACTION_LOGIN")
        loginIntentFilter.addAction("com.ydjw.ua.ACTION_LOGOUT")
        //注册广播

        //注册广播
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when(intent?.action){
                    "com.ydjw.ua.ACTION_LOGIN"-> {
                        Log.e("registerReceiver","ACTION_LOGIN")
                        tvReceive?.text = "ACTION_LOGIN"
                    }
                    "com.ydjw.ua.ACTION_LOGOUT"->{
                        Log.e("registerReceiver","ACTION_LOGOUT")
                        tvReceive?.text = "ACTION_LOGOUT"
                    }
                }

            }

        }, loginIntentFilter)

    }

    private fun initSS() {
        val imageSegmentationAnalyzer = MLAnalyzerFactory.getInstance().imageSegmentationAnalyzer //图像分割分析器
        val handKeypointAnalyzer = MLHandKeypointAnalyzerFactory.getInstance().handKeypointAnalyzer //手势识别分析器
        val analyzer = MLCompositeAnalyzer.Creator()
                .add(imageSegmentationAnalyzer)
                .add(handKeypointAnalyzer)
                .create()

        imageSegmentationAnalyzer.setTransactor(ImageSegmentAnalyzerTransactor())
        handKeypointAnalyzer.setTransactor(HandKeypointTransactor())
        val context = this.applicationContext
        val lensEngine = LensEngine.Creator(context, analyzer) // 设置摄像头前后置模式，LensEngine.BACK_LENS为后置，LensEngine.FRONT_LENS为前置。
                .setLensType(LensEngine.FRONT_LENS)
                .applyDisplayDimension(1280, 720)
                .applyFps(20.0f)
                .enableAutomaticFocus(true)
                .create()


        // 请自行实现SurfaceView控件的其他逻辑。
//        val mSurfaceView = SurfaceView(this)
        try {
            lensEngine.run(mSurfaceView?.holder)
        } catch (e: IOException) {
            // 异常处理逻辑。
        }


    }


    @OnClick(R.id.tv_send1,R.id.tv_send2)
    public fun  onclick(  v:View)  {
        when(v.id){
            R.id.tv_send1->sendA()
            R.id.tv_send2->sendB()
        }
    }

    private fun sendB() {

        val intent = Intent()
        intent.action="com.ydjw.ua.ACTION_LOGOUT"
        sendBroadcast(intent)
    }

    private fun sendA() {
        val intent = Intent()
        intent.action="com.ydjw.ua.ACTION_LOGIN"
        sendBroadcast(intent)

    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, FingerTabActivity::class.java)
            context.startActivity(starter)
        }
    }


    class ImageSegmentAnalyzerTransactor : MLTransactor<MLImageSegmentation?> {
        override fun transactResult(results: MLAnalyzer.Result<MLImageSegmentation?>) {
            val items = results.analyseList
            Log.e("transactResult",items.toString()+"");
            // 开发者根据需要处理识别结果，需要注意，这里只对检测结果进行处理。
            // 不可调用ML Kit提供的其他检测相关接口。
        }

        override fun destroy() {
            // 检测结束回调方法，用于释放资源等。

        }
    }

    class HandKeypointTransactor : MLTransactor<MLHandKeypoints?> {//MLHandKeypoints?
//        override fun transactResult(results:Any) {
//            val analyseList = results.analyseList
//            // 开发者根据需要处理识别结果，需要注意，这里只对检测结果进行处理。
//            // 不可调用ML Kit提供的其他检测相关接口。
//        }

        override fun destroy() {
            // 检测结束回调方法，用于释放资源等。
        }


        override fun transactResult(p0: MLAnalyzer.Result<MLHandKeypoints?>?) {

        }
    }
}


