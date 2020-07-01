package com.example . androidqdemo.ac

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.alibaba.fastjson.JSON
import com.example.androidqdemo.R
import com.example.androidqdemo.adapter.FileAdapter
import com.example.androidqdemo.adapter.MeiziAdapter
import com.example.androidqdemo.base.util.ToastUtils
import com.example.androidqdemo.base.util.UiHandler
import com.example.androidqdemo.bean.FileEntity
import com.example.androidqdemo.bean.MeiziBean
import com.example.androidqdemo.bean.MeiziDetailBean
import com.example.androidqdemo.constant.ApiConstant
import com.example.androidqdemo.manager.OkHttpUtils
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.File
import java.io.FileDescriptor
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log


class CacheActivity : AppCompatActivity() {
    @JvmField
    @BindView(R.id.recycler_view)
    var mRecyclerView: RecyclerView? = null
    @JvmField
    @BindView(R.id.sw)
    var sw: SwipeRefreshLayout? = null
    @JvmField
    @BindView(R.id.vg_loadmore)
    var vgLoad: ViewGroup? = null

    var page : Int  =  1;
    var mList: MutableList<MeiziDetailBean>? = null
    var fileAdapter: MeiziAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)
        ButterKnife.bind(this)
        sw?.isRefreshing ?: true
        sw?.setOnRefreshListener {
            page=1
            getData(page);
        }

        vgLoad?.setOnClickListener {
            page++
            getData(page);
        }
        initRecyclerView()
        getData(1);
    }

    private fun getData(page : Int) {

       val url :String= "https://gank.io/api/v2/data/category/Girl/type/Girl/page/$page/count/10";
        OkHttpUtils.getInstance(this)?.get(url,object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                sw?.isRefreshing=false
                UiHandler.post {
                    ToastUtils.show(
                            context =this@CacheActivity,
                            message = e.message
                    )
                }

            }

            override fun onResponse(call: Call, response: Response) {
                UiHandler.post {
                    if (response.isSuccessful) {
                        sw?.isRefreshing=false
                        val body = response.body?.string()

                        Log.e("onResponse",body)
                        val parseObject = JSON.parseObject(body, MeiziBean::class.java)
                        val data = parseObject.data
                        if(page==1){
                            mList= data as MutableList<MeiziDetailBean>?

                        }else{
                            mList?.addAll(data as MutableList<MeiziDetailBean>)
                        }
                        fileAdapter?.data = mList as List<MeiziDetailBean?>?
                        fileAdapter?.notifyDataSetChanged()
                    }
                }

            }

        })
    }

    private fun initRecyclerView() {
        mList = ArrayList()
        mRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        fileAdapter = MeiziAdapter(this)
        mRecyclerView!!.adapter = fileAdapter
        fileAdapter!!.data = mList as List<MeiziDetailBean?>?
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, CacheActivity::class.java)
            context.startActivity(starter)
        }
    }

}