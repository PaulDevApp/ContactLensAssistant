package com.appsforlife.contactlensmanagement.presentation.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
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

        context?.let {
            PreferenceManager.getDefaultSharedPreferences(it)
                .registerOnSharedPreferenceChangeListener(this)
        }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressedCallBack()
    }

    private fun onBackPressedCallBack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPresses()
                }
            })
    }

    private fun backPresses() {
        requireActivity().supportFragmentManager.popBackStack(MainFragment.NAME, 0)
    }

    companion object {

        private const val APP_SETTINGS = "app_settings"
        private const val KEY_THEME_MODE_AUTO = -1
        private const val KEY_THEME_MODE_NIGHT_NO = 1
        private const val KEY_THEME_MODE_NIGHT_YES = 2

        const val KEY_SPLASH_TOGGLE = "splash_toggle"
        const val KEY_THEME_MODE = "key_mode"

        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
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
}