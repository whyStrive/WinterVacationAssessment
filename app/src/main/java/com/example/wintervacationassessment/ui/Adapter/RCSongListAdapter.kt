package com.example.wintervacationassessment.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wintervacationassessment.Model.RCSongList
import com.example.wintervacationassessment.R
import com.example.wintervacationassessment.util.MyApplication
import com.example.wintervacationassessment.util.showToast

/**
 * Author by why
 * Date on 2022/2/14
 * Time on 15:23
 * Email: why_wanghy@qq.com
 */
class RCSongListAdapter(val rcSongList: ArrayList<RCSongList>) :
    RecyclerView.Adapter<RCSongListAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.findViewById(R.id.iv_rcSongList)
        val tv: TextView = view.findViewById(R.id.tv_rcSongList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rcsonglist_rv, parent, false)
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
        val RCSongList = rcSongList[position]
        //加载图片
        Glide
            .with(holder.iv)
            .load(RCSongList.picUrl)
            //未加载出来时，使用app图标占位
            .placeholder(R.drawable.ic_app)
            .into(holder.iv)
        //设置歌单描述
        holder.tv.text = RCSongList.name
    }

    override fun getItemCount(): Int = rcSongList.size
}
