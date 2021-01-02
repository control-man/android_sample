package com.jinss.android.androidsample.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinss.android.androidsample.R

class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_activiity)

        val firstFragment = SettingsFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.settings_container, firstFragment).commit()
    }
}
