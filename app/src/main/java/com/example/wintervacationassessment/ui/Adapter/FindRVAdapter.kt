package com.example.wintervacationassessment.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.wintervacationassessment.Model.FindRV
import com.example.wintervacationassessment.R
import com.example.wintervacationassessment.util.showToast

/**
 * Author by why
 * Date on 2022/2/13
 * Time on 21:58
 * Email: why_wanghy@qq.com
 */
class FindRVAdapter(val findList:List<FindRV>) :
    RecyclerView.Adapter<FindRVAdapter.ViewHolder>(){
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val findRViv:ImageView=view.findViewById(R.id.findRViv)
        val findRVtv:TextView=view.findViewById(R.id.findRVtv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_find_rv, parent, false)
        val viewHolder=ViewHolder(view)
        viewHolder.findRViv.setOnClickListener{
            "没做好".showToast()
        }
        viewHolder.findRVtv.setOnClickListener{
            "没做好".showToast()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val findRV=findList[position]
        holder.findRViv.setImageResource(findRV.imageId)
        holder.findRVtv.text=findRV.text
    }

    override fun getItemCount(): Int =findList.size
}