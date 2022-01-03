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
        if (key == KEY_THEME_MODE) {
            val prefs = sharedPreferences?.getString(key, "-1")

            when (prefs?.toInt()) {
                KEY_THEME_MODE_AUTO -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
                KEY_THEME_MODE_NIGHT_NO -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                KEY_THEME_MODE_NIGHT_YES -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        }
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        if (preference is SwitchPreferenceCompat) {
            if (preference.key == KEY_SPLASH_TOGGLE) {
                if (preference.isChecked) {
                    sharedPreferences?.edit()?.putBoolean(KEY_SPLASH_TOGGLE, true)?.apply()
                } else {
                    sharedPreferences?.edit()?.putBoolean(KEY_SPLASH_TOGGLE, false)?.apply()
                }
            }
            return true
        }
        return true
    }

    companion object {

        private const val APP_SETTINGS = "app_settings"
        const val KEY_SPLASH_TOGGLE = "splash_toggle"
        const val KEY_THEME_MODE = "key_mode"
        const val KEY_THEME_MODE_AUTO = -1
        const val KEY_THEME_MODE_NIGHT_NO = 1
        const val KEY_THEME_MODE_NIGHT_YES = 2

        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}