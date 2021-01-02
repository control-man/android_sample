package com.jinss.android.androidsample.theme

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.DropDownPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.jinss.android.androidsample.R
import java.util.prefs.PreferenceChangeListener

const val PREF_THEME_KEY = "pref_theme"

class SettingsFragment : PreferenceFragmentCompat() {

    private val pref by lazy {
        findPreference<DropDownPreference>(PREF_THEME_KEY)
    }
    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, value ->
        if (value == PREF_THEME_KEY) {
            pref?.summary = pref?.entry
            activity?.baseContext?.let {
                setUiTheme(it)
            }
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        pref?.summary = pref?.entry
    }

    override fun onDestroy() {
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        super.onDestroy()
    }
}
