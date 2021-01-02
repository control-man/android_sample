package com.jinss.android.androidsample.theme

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jinss.android.androidsample.R

class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_activiity)

        val firstFragment = SettingsFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.settings_container, firstFragment).commit()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("JinssSample", "onConfigurationChanged")
    }
}
