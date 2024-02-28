package com.rangerleo.skin.demo

import android.app.Application
import com.rangerleo.skin.core.SkinManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this)
    }
}