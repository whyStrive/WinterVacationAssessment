package com.example.wintervacationassessment.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wintervacationassessment.MainActivity
import com.example.wintervacationassessment.R
import com.example.wintervacationassessment.model.SearchRV
import com.example.wintervacationassessment.model.SongsBean
import com.example.wintervacationassessment.ui.adapter.SearchRVAdapter
import com.example.wintervacationassessment.viewmodel.SearchFragViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Author by why
 * Date on 2022/2/15
 * Time on 11:48
 * Email: why_wanghy@qq.com
 */
class SearchFragment(private val editText: EditText) : Fragment(R.layout.fragment_search) {
    //SearchFragment的vm
    private lateinit var vm: SearchFragViewModel
    //rv的数组
    private val songList = ArrayList<SearchRV>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //使用ViewModelProvider构造ViewModel
        vm = ViewModelProvider(requireActivity()).get(SearchFragViewModel::class.java)
        vm.searchSongs(editText.text.toString()) {
            initSearchRV(it)
            val rv: RecyclerView = view.findViewById(R.id.rv_search)
            val adapter = SearchRVAdapter(songList, this)
            rv.post {
                rv.adapter = adapter
                rv.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    //初始化rv的函数
    private fun initSearchRV(songsBean: SongsBean) {
        songList.clear()
        val len = songsBean.songsData?.songs?.size
        len?.let { length ->
            songList.addAll((0 until length).map {
                SearchRV(
                    songsBean.songsData.songs[it].name.toString(),
                    songsBean.songsData.songs[it].id
                )
            })
        }
    }
}