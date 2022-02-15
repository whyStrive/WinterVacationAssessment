package com.example.wintervacationassessment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.wintervacationassessment.model.MusicUrlBean
import com.example.wintervacationassessment.model.SongCanUseBean
import com.example.wintervacationassessment.model.SongsBean
import com.example.wintervacationassessment.util.sendRequestWithOkHttp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Author by why
 * Date on 2022/2/15
 * Time on 12:37
 * Email: why_wanghy@qq.com
 */
class SearchFragViewModel:ViewModel() {
    companion object{
        lateinit var songs:SongsBean
        lateinit var canUse:SongCanUseBean
        lateinit var musicUrl:MusicUrlBean
    }

    //搜索使用的网络请求
    fun searchSongs(keyWord:String?,callback: (SongsBean) -> Unit) {
        Log.e("TAG", "http://redrock.udday.cn:2022/cloudsearch?keywords=$keyWord" )
        //获取json
        "http://redrock.udday.cn:2022/cloudsearch?keywords=$keyWord".sendRequestWithOkHttp {
            //解析
            val songsBean = searchSongsWithGSON(it)
            //回调
            callback(songsBean)
        }
    }

    //搜索使用的解析
    private fun searchSongsWithGSON(jsonData: String): SongsBean {
        //创建对象
        val gson = Gson()
        //用自带方法取得类型
        val typeOf = object : TypeToken<SongsBean>() {}.type
        //解析
        songs = gson.fromJson(jsonData, typeOf)
        //返回数据
        return songs
    }

    //判断歌曲是否可用
    fun searchSongsCanUse(id:String?,callback: (SongCanUseBean) -> Unit) {
        //获取json
        "http://redrock.udday.cn:2022/check/music?id=$id".sendRequestWithOkHttp {
            //解析
            val canUse = searchSongCanUseWithGSON(it)
            //回调
            callback(canUse)
        }
    }
    //判断是否可用的解析
    fun  searchSongCanUseWithGSON(jsonData: String):SongCanUseBean{
        //创建对象
        val gson = Gson()
        //用自带方法取得类型
        val typeOf = object : TypeToken<SongCanUseBean>() {}.type
        //解析
        canUse = gson.fromJson(jsonData, typeOf)
        //返回数据
        return canUse
    }

    //获取音乐url
    fun getMusicUrl(id: String?,callback: (MusicUrlBean) -> Unit) {
        //获取json
        "http://redrock.udday.cn:2022/song/url?id=$id".sendRequestWithOkHttp {
            //解析
            val musicUrlBean = getMusicUrlWithGSON(it)
            //回调
            callback(musicUrlBean)
        }
    }

    //解析获取音乐url
    fun  getMusicUrlWithGSON(jsonData: String):MusicUrlBean{
        //创建对象
        val gson = Gson()
        //用自带方法取得类型
        val typeOf = object : TypeToken<MusicUrlBean>() {}.type
        //解析
        musicUrl = gson.fromJson(jsonData, typeOf)
        //返回数据
        return musicUrl
    }
}