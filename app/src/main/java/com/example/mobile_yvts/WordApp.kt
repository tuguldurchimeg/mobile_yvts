package com.example.mobile_yvts

import android.app.Application
import com.example.mobile_yvts.data.AppContainer
import com.example.mobile_yvts.data.SettingsPreferences

class WordApp : Application() {
    lateinit var container: AppContainer
    lateinit var settingsPreferences: SettingsPreferences

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
        settingsPreferences = SettingsPreferences(this)
    }
}
