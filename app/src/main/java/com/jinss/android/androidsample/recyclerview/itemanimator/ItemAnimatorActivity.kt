package com.jinss.android.androidsample.recyclerview.itemanimator

import android.content.Context
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.jinss.android.androidsample.R
import kotlinx.android.synthetic.main.activity_item_animator.*


//직접 LinearSmoothScroller 상속받아서 사용하는 예제. duration 변화를 주고싶을때
//https://medium.com/@tejumoladavid_91868/how-to-implement-an-horizontal-timed-scrolling-recyclerview-in-android-e4da369532f0

//직접 ObjectAnimator 사용해서 구현한 예제, 리사이클러뷰는 절대값으로 커스텀해서 scrollY로 이동하는게 불가능함.
//https://greedy0110.tistory.com/41?category=675211

var scrollLayoutManager: ScrollingLinearLayoutManager? = null
class ItemAnimatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_animator)

        val myData = arrayListOf<MyItem>()
        var i = 0
        for (i in 0..500) {
            myData.add(MyItem("test $i"))
        }

        scrollLayoutManager =  ScrollingLinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false,
            450
        )
        with(item_animator_recyclerview) {
            layoutManager = scrollLayoutManager
            adapter = ItemAdapter().apply {
                data = myData
            }
        }

        scroll_button.setOnClickListener {
            Log.d("jh2174", "button clicked!")
            item_animator_recyclerview.smoothScrollToPosition(450)
        }
    }
}

class ScrollingLinearLayoutManager(
    context: Context?,
    orientation: Int,
    reverseLayout: Boolean,
    private val duration: Int
) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView, state: RecyclerView.State,
        position: Int
    ) {
        val firstVisibleChild: View = recyclerView.getChildAt(0)
        val itemHeight: Int = firstVisibleChild.getHeight()
        val currentPosition = recyclerView.getChildLayoutPosition(firstVisibleChild)
        var distanceInPixels = Math.abs((currentPosition - position) * itemHeight)
        if (distanceInPixels == 0) {
            distanceInPixels = Math.abs(firstVisibleChild.getY()) as Int
        }
        val smoothScroller = SmoothScroller(
            recyclerView.context, distanceInPixels,
            duration
        )
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    private inner class SmoothScroller(context: Context?, distanceInPixels: Int, duration: Int) :
        LinearSmoothScroller(context) {
        private val distanceInPixels: Float
        private val duration: Float
        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@ScrollingLinearLayoutManager
                .computeScrollVectorForPosition(targetPosition)
        }

        override fun calculateTimeForScrolling(dx: Int): Int {
            val proportion = dx.toFloat() / distanceInPixels
            return (duration * proportion).toInt()
        }

        init {
            this.distanceInPixels = distanceInPixels.toFloat()
            this.duration = duration.toFloat()
        }
    }
}