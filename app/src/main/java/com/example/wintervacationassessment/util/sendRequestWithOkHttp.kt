package com.example.wintervacationassessment.util

import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import kotlin.concurrent.thread

/**
 * Author by why
 * Date on 2022/2/13
 * Time on 14:13
 * Email: why_wanghy@qq.com
 */

//总是写okHttp的网络请求好麻烦呀，要不封装了吧，还能偷懒（手动狗头）
//高阶函数真的骚啊，真好用
fun String.sendRequestWithOkHttp(callback:(String)->Unit):String{
    //lateinit出了点问题，还是初始化一下吧。。。
    var responseData =""
    thread {
        try {
            //创建一个OkHttpClient的实例
            val client= OkHttpClient()
            //创建request对象
            val request= Request.Builder()
                    //设置url，由于是String类的扩展函数所以直接传this
                .url(this)
                .build()
            //创建call对象，并调用方法获取数据
            val response=client.newCall(request).execute()
            //得到返回数据的具体内容
            responseData= response.body?.string().toString()
            callback(responseData)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    //返回得到的数据
    return  responseData
}