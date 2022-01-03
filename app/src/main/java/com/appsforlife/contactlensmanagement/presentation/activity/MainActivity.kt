package com.appsforlife.contactlensmanagement.presentation.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.presentation.fragments.MainFragment
import com.appsforlife.contactlensmanagement.presentation.fragments.SettingsFragment
import com.appsforlife.contactlensmanagement.presentation.fragments.SplashScreenFragment


class MainActivity : AppCompatActivity() {

    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        val isSplash = sharedPreferences?.getBoolean(SettingsFragment.KEY_SPLASH_TOGGLE, true)
        val mode: Int = sharedPreferences?.getString(SettingsFragment.KEY_THEME_MODE, "-1")?.toInt() ?: -1

        AppCompatDelegate.setDefaultNightMode(mode)

        if (savedInstanceState == null) {
            if (isSplash == true) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, SplashScreenFragment.newInstance())
                    .commit()
            } else {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, MainFragment.newInstance())
                    .commit()
            }
        }

    }

}





