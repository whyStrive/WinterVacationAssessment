package com.example.wintervacationassessment.model

import com.google.gson.annotations.SerializedName

/**
 * Author by why
 * Date on 2022/2/15
 * Time on 22:49
 * Email: why_wanghy@qq.com
 */

//精品歌单
data class GoodSongListBean(
            @SerializedName("playlists")
            val goodSongList:List<GoodSongListData>?=null
        )
data class GoodSongListData(
    @SerializedName("name")
    val goodSongListName:String?=null,
    @SerializedName("coverImgUrl")
    val goodSongListUrl:String?=null
)