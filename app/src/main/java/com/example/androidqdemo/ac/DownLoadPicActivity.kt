package com.example.androidqdemo.ac

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.alibaba.fastjson.JSON
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.example.androidqdemo.R
import com.example.androidqdemo.adapter.DownLoadPicAdapter
import com.example.androidqdemo.adapter.setListener
import com.example.androidqdemo.base.util.GlideApp
import com.example.androidqdemo.base.util.ToastUtils
import com.example.androidqdemo.base.util.UiHandler
import com.example.androidqdemo.bean.MeiziBean
import com.example.androidqdemo.bean.MeiziDetailBean
import com.example.androidqdemo.manager.OkHttpUtils
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.Exception
import java.util.*


class DownLoadPicActivity : AppCompatActivity() {
    @JvmField
    @BindView(R.id.recycler_view)
    var mRecyclerView: RecyclerView? = null
    @JvmField
    @BindView(R.id.sw)
    var sw: SwipeRefreshLayout? = null
    @JvmField
    @BindView(R.id.vg_loadmore)
    var vgLoad: ViewGroup? = null

    private var page : Int  =  1;
    var mList: MutableList<MeiziDetailBean>? = null
    var fileAdapter: DownLoadPicAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_down_load)
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

       val url = "https://gank.io/api/v2/data/category/Girl/type/Girl/page/$page/count/10"
        OkHttpUtils.getInstance(this)?.get(url,object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                sw?.isRefreshing=false
                UiHandler.post {
                    ToastUtils.show(
                            context =this@DownLoadPicActivity,
                            message = e.message
                    )
                }

            }

            override fun onResponse(call: Call, response: Response) {
                UiHandler.post {
                    if (response.isSuccessful) {
                        sw?.isRefreshing=false
                        val body = response.body?.string()

                        val parseObject = JSON.parseObject(body, MeiziBean::class.java)
                        val data = parseObject.data
                        if(page==1){
                            mList= data as MutableList<MeiziDetailBean>?

                        }else{
                            mList?.addAll(data as MutableList<MeiziDetailBean>)
                        }
                        fileAdapter?.data = mList as List<MeiziDetailBean?>?
                        fileAdapter?.notifyDataSetChanged()
                        downLoadPic()
                    }
                }

            }

        })
    }

    private fun downLoadPic() {






        for (ben in 0 until mList!!.size){

            val get = mList!![ben]
            var  v=0F;
            Thread(Runnable {
                val get1: FutureTarget<Bitmap> = GlideApp.with(this).asBitmap()
                        .load(get.url)
//                        .load("http://192.168.5.228:8080/fileserver/file/get/1594351588182.jpg")
                        .submit()
                try {
                    val get2 = get1.get()
                    UiHandler.post {
                        get.prgoress=1f
                        get.bm=get2
                        fileAdapter?.notifyItemChanged(ben)
                    }
                }catch (e : Exception){
                    UiHandler.post {
                        get.prgoress=0.5f
                        fileAdapter?.notifyItemChanged(ben)
                    }
                    e.printStackTrace()
                }
            }).start()
        }
    }



    private fun initRecyclerView() {
        mList = ArrayList()
        mRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        fileAdapter = DownLoadPicAdapter(this)
        mRecyclerView!!.adapter = fileAdapter
        fileAdapter!!.data = mList as List<MeiziDetailBean?>?
        fileAdapter!!.setListener(object :DownLoadPicAdapter.Listener{
            override fun click(item: MeiziDetailBean, position: Int) {
                Thread(Runnable {
                    val get1: FutureTarget<Bitmap> = Glide.with(this@DownLoadPicActivity).asBitmap()
                            .load(item.url)
                            .submit()
                    try {
                        val get2 = get1.get()
                        UiHandler.post {
                            item.prgoress=1f
                            item.bm=get2
                            fileAdapter?.notifyItemChanged(position)
                        }
                    }catch (e : Exception){
                        UiHandler.post {
                            item.prgoress=0.5f
                            fileAdapter?.notifyItemChanged(position)
                        }
                        e.printStackTrace()
                    }
                }).start()

            }

        })
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, DownLoadPicActivity::class.java)
            context.startActivity(starter)
        }
    }

}