package com.jinss.android.androidsample.theme

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

const val THEME_DEVICE = "2"
const val THEME_LIGHT = "1"
const val THEME_DARK = "0"

fun setUiTheme(context: Context, uiMode: String = getUiTheme(context)) {
    Log.d("JinssSample", "uiMode $uiMode")
    val mode = when(uiMode) {
        THEME_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
        THEME_DARK -> AppCompatDelegate.MODE_NIGHT_YES
        else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }
    Log.d("JinssSample", "mode: $mode")
    // Mode가 Light혹은 Dark로 지정되면, System상에서 Dark & Light를 바꿔도 따라가지 않게된다.
    AppCompatDelegate.setDefaultNightMode(mode)
}

private fun getUiTheme(context: Context): String {
    return PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_THEME_KEY, THEME_DEVICE).toString()
}
