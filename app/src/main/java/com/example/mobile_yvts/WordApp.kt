package com.example.mobile_yvts

import android.app.Application
import androidx.work.*
import com.example.mobile_yvts.data.AppContainer
import com.example.mobile_yvts.data.NotificationWorker
import com.example.mobile_yvts.data.SettingsPreferences
import java.util.concurrent.TimeUnit

class WordApp : Application() {
    lateinit var container: AppContainer
    lateinit var settingsPreferences: SettingsPreferences

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
        settingsPreferences = SettingsPreferences(this)

        val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(
            1, TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "daily_notification_work",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}
