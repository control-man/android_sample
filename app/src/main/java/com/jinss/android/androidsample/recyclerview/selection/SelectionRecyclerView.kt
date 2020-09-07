package com.jinss.android.androidsample.recyclerview.selection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jinss.android.androidsample.R
import kotlinx.android.synthetic.main.activity_selection_recycler_view.*

class SelectionRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_recycler_view)

        val mockItem = mutableListOf<Genre>()
        var genreAdapter: GenreAdapter?
        mockItem.add(Genre("favorite")); mockItem.add(Genre("recent"))
        mockItem.add(Genre("movie")); mockItem.add(Genre("sports"))
        mockItem.add(Genre("entertainment")); mockItem.add(Genre("music"))
        with(selection_recycler_view) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        genreAdapter = GenreAdapter()
        selection_recycler_view.adapter = genreAdapter
        genreAdapter.submitList(mockItem)


        var count = 0

        with(selection_button) {
            selection_button.setOnClickListener {
                // 중간에 추가해보기

                val tmpMock = mockItem.toMutableList()
                tmpMock.add(0, Genre("추가" + count.toString()))
                count++
                tmpMock.add(0, Genre("추가" + count.toString()))
                count++

                // http://blog.naver.com/jowendy/221791340114 다른 리스트 인스턴스를 넘겨줘야한다. 그래야 difftool이 제대로 동작한다.
                // genreAdapter.selectedPosition += 1 // onBind때 selection 감지를 하니깐. selection position 을 결정해줘야 한다.
                genreAdapter.submitList(tmpMock)
                // genreAdapter.notifyItemChanged(1) // 이걸해줘도 소용없는게 비동기적으로 diff 비교가 이루어지기때문에 즉각적으로는 안된다.
                // 그래서 onCurrentListChanged 여기에서 하는게 낫다.
            }
        }
    }
}