package com.example.wintervacationassessment.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.wintervacationassessment.model.FindRV
import com.example.wintervacationassessment.model.RCSongList
import com.example.wintervacationassessment.model.RCSongListBean
import com.example.wintervacationassessment.ui.adapter.FragmentPagerAdapter
import com.example.wintervacationassessment.R
import com.example.wintervacationassessment.viewmodel.FindFragViewModel
import com.example.wintervacationassessment.viewmodel.FindFragViewModel.Companion.VP2List
import com.example.wintervacationassessment.ui.adapter.FindRVAdapter
import com.example.wintervacationassessment.ui.adapter.RCSongListAdapter
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
class FindFragment : Fragment(R.layout.fragment_find) {
    val findRVList = ArrayList<FindRV>()

    //轮播图使用
    private var flag = true
    private var thread: Thread? = null

    //推荐歌单使用
    val rcSongList = ArrayList<RCSongList>()

    //FindFragment的vm
    private lateinit var vm: FindFragViewModel

    //加载 “发现” 的fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        /*
        以下为轮播图
         */
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
                val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
                //设置tabLayout
                val tab = vp2.let {
                    TabLayoutMediator(
                        tabLayout,
                        it,
                        object : TabLayoutMediator.TabConfigurationStrategy {
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
        thread = thread {
            while (flag) {
                for (index in 0 until findRVList.size) {
                    Thread.sleep(3000)
                    requireActivity().runOnUiThread {
                        vp2?.setCurrentItem(index, true)
                    }
                }
            }
        }

        /*
        以下为RV
        */
        //初始化rv
        initFindRV()
        val layoutManager = LinearLayoutManager(MyApplication.context)
        //设置方向为横向
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        //获取rv的实例
        val findRV: RecyclerView = view.findViewById(R.id.find_rv)
        //设置rv的layoutManager
        findRV.layoutManager = layoutManager
        //取得adapter
        val adapter = FindRVAdapter(findRVList)
        //设置rv的adapter
        findRV.adapter = adapter

        /*
        以下为推荐歌单
        */
        vm.RCSongList {
            initRCSongList(it)
        }
        val rcSongListRV: RecyclerView = view.findViewById(R.id.rv_rcSongList)
        rcSongListRV.post {
            val rcLayoutManager = LinearLayoutManager(MyApplication.context)
            rcLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rcSongListRV.layoutManager = rcLayoutManager
            rcSongListRV.adapter = RCSongListAdapter(rcSongList)
        }

        //在这里实现下拉刷新
        //下面是swipeRefresh使用的固定写法
        val swipeRefresh: SwipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
        swipeRefresh.setColorSchemeResources(R.color.refresh)
        swipeRefresh.setOnRefreshListener {
            //刷新vp2
            refreshVP2(
                FragmentPagerAdapter(requireActivity(), fragments),
            )
            //刷新推荐歌单
            refreshRCSongList(rcSongListRV.adapter as RCSongListAdapter)
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        flag = false
        thread = null
    }

    //初始化首页rv的函数
    fun initFindRV() {
        findRVList.add(FindRV(R.drawable.ic_findrv_recommend, "每日推荐"))
        findRVList.add(FindRV(R.drawable.ic_findrv_fm, "私人FM"))
        findRVList.add(FindRV(R.drawable.ic_findrv_songlist, "歌单"))
        findRVList.add(FindRV(R.drawable.ic_findrv_list, "排行榜"))
        findRVList.add(FindRV(R.drawable.ic_findrv_live, "直播"))
        findRVList.add(FindRV(R.drawable.ic_findrv_number, "数字专辑"))
        findRVList.add(FindRV(R.drawable.ic_findrv_think, "专注冥想"))
        findRVList.add(FindRV(R.drawable.ic_findrv_songhouse, "歌房"))
        findRVList.add(FindRV(R.drawable.ic_findrv_game, "游戏专区"))
    }

    //初始化推荐歌单的函数
    fun initRCSongList(rc: RCSongListBean) {
        rcSongList.clear()
        //为防止有重复的内容出现，设置下面的arraylist来存储已经用过的数据（暂时还没想到更好的办法，将就用了）
        val arr = ArrayList<Int>()
        var i=0
        while (i<6) {
            val r: Int = (0 until 60).random()
            if (arr.contains(r)) {
                continue
            } else {
                arr.add(r)
                rcSongList.add(
                    RCSongList(
                        rc.rcSongList?.get(r)?.name!!,
                        rc.rcSongList.get(r).picUrl.toString()
                    )
                )
                i++
            }
        }
        arr.clear()
    }

    //刷新vp2的函数
    @SuppressLint("NotifyDataSetChanged")
    fun refreshVP2(
        adapter: FragmentPagerAdapter
    ) {
        adapter.notifyDataSetChanged()
    }

    //刷新推荐歌单的函数
    @SuppressLint("NotifyDataSetChanged")
    fun refreshRCSongList(
        adapter: RCSongListAdapter,
    ) {
        //刷新数据
        vm.RCSongList {
            initRCSongList(it)
            requireActivity().runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }
}