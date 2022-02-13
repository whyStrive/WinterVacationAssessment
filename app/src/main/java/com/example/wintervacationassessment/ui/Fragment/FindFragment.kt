package com.example.wintervacationassessment.ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.wintervacationassessment.Model.FindRV
import com.example.wintervacationassessment.ui.Adapter.FragmentPagerAdapter
import com.example.wintervacationassessment.R
import com.example.wintervacationassessment.ViewModel.FindFragViewModel
import com.example.wintervacationassessment.ViewModel.FindFragViewModel.Companion.VP2List
import com.example.wintervacationassessment.ui.Adapter.FindRVAdapter
import com.example.wintervacationassessment.util.MyApplication
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.concurrent.thread

/**
 * Author by why
 * Date on 2022/2/11
 * Time on 20:52
 * Email: why_wanghy@qq.com
 */
class FindFragment : Fragment() {
     val findRVList=ArrayList<FindRV>()
    //轮播图使用
    private var flag=true
    private var thread:Thread?=null

    private lateinit var vm: FindFragViewModel

    //加载 “发现” 的fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    //以下为轮播图
        //加载布局
        val view =  inflater.inflate(R.layout.fragment_find, container, false)
        //使用ViewModelProvider构造ViewModel
        vm = ViewModelProvider(requireActivity()).get(FindFragViewModel::class.java)
        //获取到vp2的实例
        val vp2: ViewPager2? = view.findViewById(R.id.view_pager)
        //创建容纳VPFragment实例的ArrayList数组
        val fragments = ArrayList<Fragment>()
        //网络请求部分
        vm.VP2 {
            //获取总的长度
            val len = it.banner?.size ?: 0
            //利用得到的数据创建Fragment实例并添加至fragments数组中
            fragments.addAll((0 until len).map {
                //向构造函数传入图片url创建实例
                VPFragment(VP2List.banner?.get(it)?.pic ?: "")
            })
            //放到主线程执行，其他线程无法更新ui
            vp2?.post {
                //vp2适配器
                vp2.adapter = FragmentPagerAdapter(requireActivity(), fragments)
                //获取tabLayout实例
                val tabLayout:TabLayout=view.findViewById(R.id.tabLayout)
                //设置tabLayout
                val tab= vp2.let {
                    TabLayoutMediator(tabLayout, it,object:TabLayoutMediator.TabConfigurationStrategy{
                        override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                            //不知道弄什么好，就弄个“·”吧
                            tab.setText("·")
                        }
                    })
                }
                //attch
                tab.attach()
            }
        }
        //设置轮播（简易版）
        thread =  thread {
            while(flag){
                for (index in 0..2){
                    Thread.sleep(3000)
                    requireActivity().runOnUiThread{
                        vp2?.setCurrentItem(index,true)
                    }
                }
            }
        }

    //以下为RV
        //初始化rv
        initFindRV()
        val layoutManager=LinearLayoutManager(MyApplication.context)
        //设置方向为横向
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        //获取rv的实例
        val findRV:RecyclerView=view.findViewById(R.id.find_rv)
        //设置rv的layoutManager
        findRV.layoutManager=layoutManager
        //取得adapter
        val adapter=FindRVAdapter(findRVList)
        //设置rv的adapter
        findRV.adapter=adapter
        //返回view
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        flag=false
        thread=null
    }

    fun initFindRV(){
        findRVList.add(FindRV(R.drawable.ic_findrv_recommend,"每日推荐"))
        findRVList.add(FindRV(R.drawable.ic_findrv_fm,"私人FM"))
        findRVList.add(FindRV(R.drawable.ic_findrv_songlist,"歌单"))
        findRVList.add(FindRV(R.drawable.ic_findrv_list,"排行榜"))
        findRVList.add(FindRV(R.drawable.ic_findrv_live,"直播"))
        findRVList.add(FindRV(R.drawable.ic_findrv_number,"数字专辑"))
        findRVList.add(FindRV(R.drawable.ic_findrv_sleep,"助眠解压"))
        findRVList.add(FindRV(R.drawable.ic_findrv_songhouse,"歌房"))
        findRVList.add(FindRV(R.drawable.ic_findrv_game,"游戏专区"))
    }
}