package com.jinss.android.androidsample.theme

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.jinss.android.androidsample.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
