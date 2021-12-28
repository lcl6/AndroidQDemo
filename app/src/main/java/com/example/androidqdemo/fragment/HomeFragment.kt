package com.example.androidqdemo.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.androidqdemo.R
import com.example.androidqdemo.ac.*
import com.lodz.android.pandora.base.fragment.BaseFragment
import java.io.FileOutputStream
import java.io.InputStream

/**
 *Created by liancl on 2020/11/9 0009.
 */

class HomeFragment : BaseFragment() {

    companion object{
        fun  getInstance():HomeFragment{
            val homeFragment = HomeFragment()
            return homeFragment;
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fg_home
    }


    override fun findViews(view: View, savedInstanceState: Bundle?) {
        super.findViews(view, savedInstanceState)
        ButterKnife.bind(this,view)
        showStatusCompleted()
    }

    override fun initData(view: View) {
        super.initData(view)
    }

    override fun setListeners(view: View) {
        super.setListeners(view)
    }

    @OnClick(R.id.tv_test_q, R.id.tv_ts_cj,R.id.tv_test_saf,
            R.id.tv_ts_cache,R.id.tv_down_pic,R.id.tv_location,
            R.id.webview,R.id.websocket ,R.id.rv_ed,
            R.id.tv_ffsj,R.id.tv_cjh,R.id.tv_rxf,R.id.tv_zlgx,
            R.id. tv_viewpager2,R.id.tv_asp,R.id.tv_view,R.id.tv_flutter,
            R.id.tv_webview
    )
    fun onclick(v: View) {
        when (v.id) {
            R.id.tv_test_q -> startActivity(Intent(context, AndroidQActivity::class.java))
            R.id.tv_ts_cj -> StatusActivity.Companion.start(context)
            R.id.tv_test_saf -> showFileChooser()
            R.id.tv_ts_cache ->CacheActivity.start(context)
            R.id.tv_down_pic ->DownLoadPicActivity.start(context)
            R.id.tv_location ->LocationActivity.start(context)
            R.id.webview -> WebViewActivity.start(context)
            R.id.websocket -> WebSocketActivity.start(context)
            R.id.rv_ed-> EditextRecyclerViewPicActivity.start(context)
            R.id.tv_ffsj-> DisaptchActivity.Companion.start(context)
            R.id.tv_cjh->PluginActivity.Companion.start(context)
            R.id.tv_rxf->HotFixActivity.Companion.start(context)
            R.id.tv_zlgx->IncreatUpdateActivity.Companion.start(context)
            R.id. tv_viewpager2->Viewpager2Activity.Companion.start(context)
            R.id. tv_asp->AspActivity.Companion.start(context)
            R.id. tv_view->ViewActivity.Companion.start(context)
            R.id.tv_flutter->FlutterTestActivity.Companion.start(context)
            R.id.tv_webview->VideoActivity.Companion.start(context)

        }
    }


    private fun showFileChooser() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        // 只显示可以打开的文件
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        // 可选择所有文件类型
        intent.type = "*/*"
        // 只可选择jpeg图片
        // intent.type = "image/jpeg"
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            data?.data?.let {
                //copy 一份到本地文件夹
                val inputStream = activity?.contentResolver?.openInputStream(it)
                if (inputStream != null) {
                    writeToLocal(context.externalCacheDir?.path+System.currentTimeMillis()+".db",inputStream)
                };
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun writeToLocal(destination: String?, input: InputStream) {
        var index: Int
        val bytes = ByteArray(1024)
        val downloadFile = FileOutputStream(destination)
        while (input.read(bytes).also { index = it } != -1) {
            downloadFile.run {
                write(bytes, 0, index)
                flush()
            }
        }
        input.close()
        downloadFile.close()
    }
}