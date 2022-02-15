package com.example.wintervacationassessment.ViewModel

import androidx.lifecycle.ViewModel
import com.example.wintervacationassessment.Model.BannerBean
import com.example.wintervacationassessment.Model.RCSongListBean
import com.example.wintervacationassessment.util.sendRequestWithOkHttp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Author by why
 * Date on 2022/2/12
 * Time on 16:39
 * Email: why_wanghy@qq.com
 */

//创建FindFragment对应的ViewModel类（虽然没搞懂MVVM架构，但还是整整ViewModel吧......）
class FindFragViewModel() : ViewModel() {
    companion object {
        lateinit var VP2List: BannerBean
        lateinit var RCList:RCSongListBean
    }

    //VP2使用
    fun VP2(callback: (BannerBean) -> Unit) {
        //获取json数据并解析
        "http://redrock.udday.cn:2022/banner?type=1".sendRequestWithOkHttp {
            //用gson解析请求到的数据
            val bean = bannerBeanWithGSON(it)
            //回调
            callback(bean)
        }
    }

    //推荐歌单使用
    fun RCSongList(callback: (RCSongListBean) -> Unit){
        //获取json
        "http://redrock.udday.cn:2022/personalized?limit=60".sendRequestWithOkHttp {
            //解析
            val rcSongListBean=rcSongListBeanWithGSON(it)
            //回调
            callback(rcSongListBean)
        }
    }

    //VP2的gson处理
    private fun bannerBeanWithGSON(jsonData: String): BannerBean {
        //创建对象
        val gson = Gson()
        //用自带方法取得类型
        val typeOf = object : TypeToken<BannerBean>() {}.type
        //解析
        VP2List = gson.fromJson(jsonData, typeOf)
        //返回数据
        return VP2List
    }
    //推荐歌单的gson处理
    private fun rcSongListBeanWithGSON(jsonData: String):RCSongListBean{
        //创建对象
        val gson = Gson()
        //用自带方法取得类型
        val typeOf = object : TypeToken<RCSongListBean>() {}.type
        //解析
        RCList = gson.fromJson(jsonData, typeOf)
        //返回数据
        return RCList
    }
}


