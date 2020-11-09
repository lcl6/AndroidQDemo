package com.example.androidqdemo .ac

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TabHost
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androidqdemo.R
import com.example.androidqdemo.base.ac.BaseGreyActivity
import com.example.androidqdemo.base.adapter.BasePagerAdapter
import com.example.androidqdemo.fragment.ContentFragment
import com.example.androidqdemo.fragment.HomeFragment
import com.example.androidqdemo.fragment.MineFragment
import com.google.android.material.tabs.TabLayout
import com.lodz.android.pandora.base.activity.BaseActivity
import java.util.ArrayList

/**
 *Created by liancl on 2020/11/9 0009.
 */

class MainTabActivity : BaseGreyActivity() {

    @JvmField
    @BindView(R.id.vp)
    var mViewPage: ViewPager? =null
    @JvmField
    @BindView(R.id.tb)
    var  mTab: TabLayout ?=null


    /** 主菜单（标题） */
    private val mTitles = arrayOf(
            "首页",
            "内容",
            "我的")

    /** 主菜单（图标） */
    private val mIcons = intArrayOf(
            R.drawable.tab_home,
            R.drawable.tab_content,
            R.drawable.tab_mine)

    private var mFragments: MutableList<Fragment>? = null


    override fun getGreyLayoutId(): Int {
        return R.layout.activity_main_tab
    }

    override fun findViews(savedInstanceState: Bundle?) {
        super.findViews(savedInstanceState)
        ButterKnife.bind(this)

        initViewPage();

    }

    private fun initViewPage() {
        val instance = HomeFragment.getInstance()
        val contentInstance = ContentFragment.getInstance()
        val mineInstance = MineFragment.getInstance()
        mFragments=mutableListOf(
                instance,
                contentInstance,
                mineInstance
        )
        val mPagerAdapter = BasePagerAdapter(supportFragmentManager)
        mViewPage?.let { mPagerAdapter.setFragments(it, mFragments!!) }
        mViewPage?.adapter = mPagerAdapter
        mViewPage?.offscreenPageLimit = mFragments!!.size
        mTab?.setupWithViewPager(mViewPage)
        mTab?.addOnTabSelectedListener(object  :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

        })
        for (i in mTitles.indices) { // 循环添加自定义的tab
            val tab: TabLayout.Tab? = mTab?.getTabAt(i)
            tab?.customView = getTabView(i)
        }
    }
    private fun getTabView(position: Int): View {
        layoutInflater.inflate(R.layout.item_main_tab, mTab, false).apply {
            // View设置属性，注意上面引用的包（import属于你们自己的包路径）
            this.findViewById<ImageView>(R.id.tb_layout_img).setImageResource(mIcons[position])
            this.findViewById<TextView>(R.id.tv_layout_tv).text = mTitles[position]
            return this
        }
    }
    override fun setListeners() {
        super.setListeners()
    }

    override fun initData() {
        super.initData()
    }



}