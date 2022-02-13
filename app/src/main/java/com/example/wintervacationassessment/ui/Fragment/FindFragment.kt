package com.example.wintervacationassessment.ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wintervacationassessment.R

/**
 * Author by why
 * Date on 2022/2/11
 * Time on 20:52
 * Email: why_wanghy@qq.com
 */
class FindFragment:Fragment() {

    //加载 “发现” 的fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //将布局动态加载进来
        return inflater.inflate(R.layout.find_fragment,container,false)
    }
}