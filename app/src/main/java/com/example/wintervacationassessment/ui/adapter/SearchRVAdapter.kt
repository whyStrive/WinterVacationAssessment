package com.example.wintervacationassessment.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wintervacationassessment.model.SearchRV
import com.example.wintervacationassessment.R
import com.example.wintervacationassessment.util.showToast

/**
 * Author by why
 * Date on 2022/2/15
 * Time on 12:31
 * Email: why_wanghy@qq.com
 */
class SearchRVAdapter(val song: ArrayList<SearchRV>) :
    RecyclerView.Adapter<SearchRVAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.findViewById(R.id.iv_search)
        val tv: TextView = view.findViewById(R.id.tv_search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_rv, parent, false)
        val viewHolder = ViewHolder(view)
        /*viewHolder.iv.setOnClickListener {
            "没做好".showToast()
        }
        viewHolder.tv.setOnClickListener {
            "没做好".showToast()
        }*/
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //获取实例
        val searchRV = song[position]
        holder.tv.text=searchRV.name
    }

    override fun getItemCount(): Int = song.size
}
