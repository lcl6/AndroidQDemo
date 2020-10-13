package com.example.androidqdemo.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.androidqdemo.R
import com.example.androidqdemo.base.adapter.BaseRecyclerViewAdapter
import com.example.androidqdemo.bean.FileEntity
import com.example.androidqdemo.bean.MeiziDetailBean

/**
 * Created by liancl on 2020/6/11 0011.
 */
class MeiziAdapter(context: Context?) : BaseRecyclerViewAdapter<MeiziDetailBean?>(context) {
    override fun onBind(holder: ViewHolder, position: Int) {

        if (data.isEmpty()) {
            onBindViewHolder(holder,position);
        }else{
            setItem(holder as Viewholder, getItem(position))
        }
    }

    private fun setItem(holder: Viewholder, item: MeiziDetailBean?) {
        holder.tvTitle!!.text = item!!.views
        holder.tvPath!!.text = item.likeCounts
        holder.img_a?.let {
            Glide.with(context)
                .load(item.url)
                .into(it)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return Viewholder(getLayoutView(viewGroup, R.layout.it_meizi))
    }


    class Viewholder(itemView: View) : ViewHolder(itemView) {
         @JvmField
        @BindView(R.id.tv_title)
        var tvTitle: TextView? = null
         @JvmField
        @BindView(R.id.tv_path)
        var tvPath: TextView? = null

         @JvmField
         @BindView(R.id.img_a)
         var img_a: ImageView? = null
        init {
            ButterKnife.bind(this, itemView)
        }
    }
}