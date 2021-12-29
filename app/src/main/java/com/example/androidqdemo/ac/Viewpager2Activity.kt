package com.example.androidqdemo.ac
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.alibaba.fastjson.JSON
import com.bumptech.glide.request.FutureTarget
import com.example.androidqdemo.adapter.MyViewpagerAdapter
import com.example.androidqdemo.R
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


/**
 * 测试修改清单
 * Created by liancl on 2020/6/17 0017.
 */
class Viewpager2Activity : AppCompatActivity() {
    var mList :MutableList<MeiziDetailBean>?=null
    @JvmField
    @BindView(R.id.vp)
    var vp: ViewPager2? =null

    var myViewpagerAdapter : MyViewpagerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        ButterKnife.bind(this)


        initViewPager()
        getData(1)
    }

    private fun getData(page : Int) {

        val url = "https://gank.io/api/v2/data/category/Girl/type/Girl/page/$page/count/10"
        OkHttpUtils.getInstance(this)?.get(url,object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                UiHandler.post {
                    ToastUtils.show(
                            context =this@Viewpager2Activity,
                            message = e.message
                    )
                }

            }

            override fun onResponse(call: Call, response: Response) {
                UiHandler.post {
                    if (response.isSuccessful) {
                        val body = response.body?.string()

                        val parseObject = JSON.parseObject(body, MeiziBean::class.java)
                        val data = parseObject.data
                        if(page==1){
                            mList= data as MutableList<MeiziDetailBean>?

                        }else{
                            mList?.addAll(data as MutableList<MeiziDetailBean>)
                        }
                        myViewpagerAdapter?.data = mList as List<MeiziDetailBean?>?
                        myViewpagerAdapter?.notifyDataSetChanged()
                        downLoadPic()
                    }
                }

            }

        })
    }
    private fun initViewPager() {
        mList = ArrayList<MeiziDetailBean>() as MutableList<MeiziDetailBean>
        myViewpagerAdapter = MyViewpagerAdapter(this)
        myViewpagerAdapter?.data= mList as List<MeiziDetailBean?>?
        vp?.orientation=ViewPager2.ORIENTATION_VERTICAL
        vp?.adapter=myViewpagerAdapter


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
                        myViewpagerAdapter?.notifyItemChanged(ben)
                    }
                }catch (e : Exception){
                    UiHandler.post {
                        get.prgoress=0.5f
                        myViewpagerAdapter?.notifyItemChanged(ben)
                    }
                    e.printStackTrace()
                }
            }).start()
        }
    }

    @OnClick(R.id.flb)
    public fun click(view :View){
        when (view.id) {
            R.id.flb-> {
                val meiziDetailBean = MeiziDetailBean()
                meiziDetailBean.url="http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2"
                meiziDetailBean.views="这是新加的  老色痞"
                mList?.add(meiziDetailBean)
                (mList?.size?.minus(1))?.let { myViewpagerAdapter?.notifyItemInserted(it) }
            }
            else -> {
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, Viewpager2Activity::class.java)
            context.startActivity(starter)
        }
    }



}


