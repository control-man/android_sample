package com.jinss.android.androidsample

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.jinss.android.androidsample.livedata.eventsequence.EventSequenceActivity
import com.jinss.android.androidsample.recyclerview.itemanimator.ItemAnimatorActivity
import com.jinss.android.androidsample.recyclerview.selection.SelectionRecyclerView
import com.jinss.android.androidsample.retrofit2.RetrofitActivity
import com.jinss.android.androidsample.theme.ThemeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selection_theme_change.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }
        selection_recycler_view.setOnClickListener {
            startActivity(Intent(this, SelectionRecyclerView::class.java))
        }
        selection_sequence_event.setOnClickListener {
            startActivity(Intent(this, EventSequenceActivity::class.java))
        }
        selection_item_animator.setOnClickListener {
            startActivity(Intent(this, ItemAnimatorActivity::class.java))
        }
        retrofit_test.setOnClickListener {
            startActivity(Intent(this, RetrofitActivity::class.java))
        }
    }
}
