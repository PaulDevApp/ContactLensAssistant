package com.appsforlife.contactlensmanagement.presentation

import android.app.Application
import com.appsforlife.contactlensmanagement.di.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}