package com.jinss.android.androidsample

import android.app.Application
import com.jinss.android.androidsample.theme.setUiTheme

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setUiTheme(this)
    }
}