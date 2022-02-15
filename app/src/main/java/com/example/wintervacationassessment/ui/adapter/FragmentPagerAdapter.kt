package com.example.wintervacationassessment.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Author by why
 * Date on 2022/2/11
 * Time on 21:33
 * Email: why_wanghy@qq.com
 */

//创建轮播图vp2的适配器，并实现vp2和fragment联合
class FragmentPagerAdapter (frag:FragmentActivity, private val fragments:ArrayList<Fragment>):FragmentStateAdapter(frag){

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}