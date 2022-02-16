package com.example.wintervacationassessment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wintervacationassessment.R
import com.example.wintervacationassessment.model.GoodSongList
import com.example.wintervacationassessment.model.RCSongList
import com.example.wintervacationassessment.util.showToast

/**
 * Author by why
 * Date on 2022/2/15
 * Time on 22:56
 * Email: why_wanghy@qq.com
 */
class GoodSongLIstAdapter(val goodSongLists: ArrayList<GoodSongList>) :
    RecyclerView.Adapter<GoodSongLIstAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.findViewById(R.id.iv_goodSongList)
        val tv: TextView = view.findViewById(R.id.tv_goodSongList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_goodsonglist_rv, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.iv.setOnClickListener {
            "没做好".showToast()
        }
        viewHolder.tv.setOnClickListener {
            //文字太长显示不完的时候，点一下可以看完
            viewHolder.tv.text.toString().showToast()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //获取实例
        val goodSongList = goodSongLists[position]
        //加载图片
        Glide
            .with(holder.iv)
            .load(goodSongList.picUrl)
            //未加载出来时，使用app图标占位
            .placeholder(R.drawable.ic_app)
            .into(holder.iv)
        //设置歌单描述
        holder.tv.text = goodSongList.name
    }

    override fun getItemCount(): Int = goodSongLists.size
}
