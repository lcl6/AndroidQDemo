package com.example.androidqdemo.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import butterknife.BindView
import butterknife.ButterKnife
import com.example.androidqdemo.R
import com.example.androidqdemo.base.adapter.BaseRecyclerViewAdapter
import com.example.androidqdemo.bean.FileEntity

/**
 * Created by liancl on 2020/6/11 0011.
 */
class FileAdapter(context: Context?) : BaseRecyclerViewAdapter<FileEntity?>(context) {
    override fun onBind(holder: ViewHolder, position: Int) {
        setItem(holder as Viewholder, getItem(position))
    }

    private fun setItem(holder: Viewholder, item: FileEntity?) {
        holder.tvTitle!!.text = item!!.name
        holder.tvPath!!.text = item.path
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return Viewholder(getLayoutView(viewGroup, R.layout.it_file))
    }

     class Viewholder(itemView: View) : ViewHolder(itemView) {
         @JvmField
        @BindView(R.id.tv_title)
        var tvTitle: TextView? = null
         @JvmField
        @BindView(R.id.tv_path)
        var tvPath: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}