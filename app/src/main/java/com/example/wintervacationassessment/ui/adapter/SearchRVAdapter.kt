package com.example.wintervacationassessment.ui.adapter

import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wintervacationassessment.model.SearchRV
import com.example.wintervacationassessment.R
import com.example.wintervacationassessment.ui.fragment.FindFragment
import com.example.wintervacationassessment.ui.fragment.SearchFragment
import com.example.wintervacationassessment.util.MyApplication
import com.example.wintervacationassessment.util.showToast
import com.example.wintervacationassessment.viewmodel.SearchFragViewModel
import com.example.wintervacationassessment.viewmodel.SearchFragViewModel.Companion.songs

/**
 * Author by why
 * Date on 2022/2/15
 * Time on 12:31
 * Email: why_wanghy@qq.com
 */
class SearchRVAdapter(
    val song: ArrayList<SearchRV>,
    val frag: SearchFragment
) :
    RecyclerView.Adapter<SearchRVAdapter.ViewHolder>() {

    companion object {
        val mediaPlayer = MediaPlayer()
        var tag: String? = null
        var isInit: Boolean = false
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.findViewById(R.id.iv_search)
        val tv: TextView = view.findViewById(R.id.tv_search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search_rv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //获取实例
        val searchRV = song[position]
        holder.tv.text = searchRV.name
        //设置点击事件播放音乐
        holder.itemView.setOnClickListener {
            //调用网络请求
            val vm = ViewModelProvider(frag.requireActivity()).get(SearchFragViewModel::class.java)
            //获取数据并设置
            vm.getMusicUrl(song[position].id) {
                it.musics?.get(0).let { musicData ->
                    musicData?.url?.let { url ->
                        val play: ImageButton = frag.requireActivity().findViewById(R.id.play)
                        if (tag != url) {
                            mediaPlayer.reset()
                            //初始化
                            initMediaPlayer(url)
                            //播放
                            mediaPlayer.start()
                            //获取按钮实例并更改图片
                            play.setImageResource(R.drawable.ic_pause)
                            //获取歌名文本的实例
                            val tv: TextView = frag.requireActivity().findViewById(R.id.songName)
                            //设置歌名
                            tv.text = song[position].name
                        } else {
                            if (mediaPlayer.isPlaying) {
                                mediaPlayer.reset()
                                initMediaPlayer(url)
                                mediaPlayer.start()
                            } else {
                                mediaPlayer.reset()
                                mediaPlayer.start()
                                play.setImageResource(R.drawable.ic_pause)
                            }
                        }
                    }
                }
            }
            //获取editText的实例
            val et:EditText=frag.requireActivity().findViewById(R.id.editText)
            //获取歌曲图片的实例
            val iv:ImageView=frag.requireActivity().findViewById(R.id.songPic)
            vm.searchSongs(et.text.toString()){
                Glide.with(frag.requireActivity())
                    .load(it.songsData?.songs!![position].picUrl.toString())
                    .placeholder(R.drawable.ic_app)
                    .into(iv)
            }
        }
    }

    override fun getItemCount(): Int = song.size

    private fun initMediaPlayer(url: String) {
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        isInit = true
        tag = url
    }

}
