package com.appsforlife.contactlensmanagement.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.presentation.fragments.SettingsFragment
import com.google.android.material.textview.MaterialTextView

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment.newInstance())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpToolbar()
    }

    private fun setUpToolbar() {
        val ivBack = findViewById<ImageView>(R.id.iv_back)
        val tvTitle = findViewById<MaterialTextView>(R.id.tv_toolbar_title)
        tvTitle.text = applicationContext.resources.getString(R.string.settings)
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}