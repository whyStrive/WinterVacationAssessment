package com.example.wintervacationassessment.model

import com.google.gson.annotations.SerializedName

/**
 * Author by why
 * Date on 2022/2/15
 * Time on 12:10
 * Email: why_wanghy@qq.com
 */


data class SongsBean(
    @SerializedName("result")
    val songsData: SongsData?=null
)

data class SongsData(
    @SerializedName("songs")
    val songs:List<Song>?=null
)

data class Song(
    @SerializedName("name")
    val name:String?=null,
    @SerializedName("id")
    val id:String?=null,
    @SerializedName("al")
    val picUrl:PicUrl?=null
)

data class PicUrl(
    @SerializedName("picUrl")
    val picUrl:String?=null
)
