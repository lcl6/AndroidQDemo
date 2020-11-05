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
import com.example.androidqdemo.base.util.UiHandler
import com.example.androidqdemo.bean.FileEntity
import com.example.androidqdemo.bean.MeiziDetailBean
import com.example.androidqdemo.view.ProgressImageView

/**
 * Created by liancl on 2020/6/11 0011.
 */
class DownLoadPicAdapter(context: Context?) : BaseRecyclerViewAdapter<MeiziDetailBean?>(context) {

    lateinit var  listener : Listener

    override fun onBind(holder: ViewHolder, position: Int) {
        setItem(holder as Viewholder, getItem(position),position)
    }

    private fun setItem(holder: Viewholder, item: MeiziDetailBean?,position: Int) {
        holder.tvTitle!!.text = item!!.views
        holder.tvPath!!.text = item.likeCounts

        item.prgoress?.let { holder.img_a?.setPer(it) }

        holder.img_a?.setImageBitmap(item.bm)


        holder.img_a?.setOnClickListener {
            if (listener != null) {
                holder.img_a?.setPer(0f)
                listener?.click(item,position)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return Viewholder(getLayoutView(viewGroup, R.layout.it_progress_image))
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
        var img_a: ProgressImageView? = null
        init {
            ButterKnife.bind(this, itemView)
        }
    }


    public interface Listener{
        fun click(item: MeiziDetailBean,position: Int)
    }

}
public fun DownLoadPicAdapter.setListener(li: DownLoadPicAdapter.Listener){
    this.listener=li
}
