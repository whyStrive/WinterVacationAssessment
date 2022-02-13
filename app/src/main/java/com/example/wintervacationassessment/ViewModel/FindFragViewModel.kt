package com.example.wintervacationassessment.ViewModel

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.wintervacationassessment.Model.BannerBean
import com.example.wintervacationassessment.Model.BannerData
import com.example.wintervacationassessment.Model.FindRV
import com.example.wintervacationassessment.R
import com.example.wintervacationassessment.ui.Fragment.FindFragment
import com.example.wintervacationassessment.util.MyApplication
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
    }


    fun VP2(callback: (BannerBean) -> Unit) {
        //获取json数据并解析
        "http://redrock.udday.cn:2022/banner?type=1".sendRequestWithOkHttp {
            //用gson解析请求到的数据
            val bean = parseJSONWithGSON(it)
            //回调
            callback(bean)
        }
    }

    private fun parseJSONWithGSON(jsonData: String): BannerBean {
        //创建对象
        val gson = Gson()
        //用自带方法取得类型
        val typeOf = object : TypeToken<BannerBean>() {}.type
        //解析
        VP2List = gson.fromJson(jsonData, typeOf)
        //返回数据
        return VP2List
    }
}


