package com.example.androidqdemo.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androidqdemo.R
import com.example.androidqdemo.base.adapter.BaseRecyclerViewAdapter
import com.example.androidqdemo.base.util.GlideApp
import com.example.androidqdemo.bean.MeiziDetailBean
import com.example.androidqdemo.manager.tinker.TinkerApplicationLike

/**
 * Created by liancl on 2020/6/11 0011.
 */
 class MyViewpagerAdapter(context: Context?) : BaseRecyclerViewAdapter<MeiziDetailBean?>(context) {


    override fun onBind(holder: ViewHolder, position: Int) {
        getItem(position).let {
            if (it != null) {
                setItem(holder as Viewholder, it,position)
            }
        }
    }

    private fun setItem(holder: Viewholder, item: MeiziDetailBean, position: Int) {
        holder.img?.let {
            GlideApp.with(TinkerApplicationLike.getInstance())
                    .load(item.url)
                    .into(it)
        }
        holder.edBottom?.text = item.views
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return Viewholder(getLayoutView(viewGroup, R.layout.it_ed_item_my_viewpager))
    }

     class Viewholder(itemView: View) : ViewHolder(itemView) {


         @JvmField
         @BindView(R.id.img)
         var img: ImageView? = null
        @JvmField
        @BindView(R.id.tv_pager)
        var edBottom: TextView? = null
        init {
            ButterKnife.bind(this, itemView)
        }
    }


    public interface Listener{
        fun click(item: MeiziDetailBean,position: Int)
    }

    public fun DownLoadPicAdapter.setListener(li: DownLoadPicAdapter.Listener){
        this.listener=li
    }
}
