package com.example.mobile_yvts

import android.app.Application
import com.example.mobile_yvts.data.AppContainer

class WordApp : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
