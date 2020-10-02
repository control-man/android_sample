package com.jinss.android.androidsample.recyclerview.itemanimator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jinss.android.androidsample.R
import kotlinx.android.synthetic.main.activity_item_animator.*

class ItemAnimatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_animator)

        val myData = arrayListOf<MyItem>()
        var i = 0
        for (i in 0..100) {
            myData.add(MyItem("test"))
        }

        with(item_animator_recyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = ItemAdapter().apply {
                data = myData
            }
        }
    }
}