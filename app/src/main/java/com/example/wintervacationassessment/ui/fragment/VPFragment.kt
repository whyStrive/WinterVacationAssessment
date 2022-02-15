package com.example.wintervacationassessment.ui.Fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.wintervacationassessment.R

/**
 * Author by why
 * Date on 2022/2/12
 * Time on 16:56
 * Email: why_wanghy@qq.com
 */

//构造函数中加入url参数，后面可以直接调用
class VPFragment(private val url:String):Fragment(R.layout.vp_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //获取ImageView实例
        val iv: ImageView= view.findViewById(R.id.vp_im)
        //加载图片
        Glide
            .with(iv)
            .load(url)
            //未加载出来时，使用app图标占位
            .placeholder(R.drawable.ic_app)
            .into(iv)
    }
}