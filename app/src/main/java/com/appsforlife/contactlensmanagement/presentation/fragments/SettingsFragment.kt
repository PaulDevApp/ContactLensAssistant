package com.appsforlife.contactlensmanagement.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.appsforlife.contactlensmanagement.R

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

    private var sharedPreferences: SharedPreferences? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        sharedPreferences = context?.getSharedPreferences(
            APP_SETTINGS,
            Context.MODE_PRIVATE
        )

        PreferenceManager.getDefaultSharedPreferences(context)
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "key_mode") {
            val prefs = sharedPreferences?.getString(key, "-1")

            when (prefs?.toInt()) {
                -1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
                1 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                2 -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        }
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        if (preference is SwitchPreferenceCompat) {
            if (preference.key == "splash_toggle") {
                if (preference.isChecked) {
                    sharedPreferences?.edit()?.putBoolean("splash_toggle", true)?.apply()
                } else {
                    sharedPreferences?.edit()?.putBoolean("splash_toggle", false)?.apply()
                }
            }
            return true
        }
        return true
    }

    companion object {

        private const val APP_SETTINGS = "app_settings"

        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}