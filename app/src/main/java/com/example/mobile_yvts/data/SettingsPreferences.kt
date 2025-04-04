package com.example.mobile_yvts.data

import android.content.Context

class SettingsPreferences(context: Context) {
    private val settingsPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    fun saveSelectedLanguage(language: String) {
        settingsPreferences.edit().putString("selected_language", language).apply()
    }

    fun getSelectedLanguage(): String? {
        return settingsPreferences.getString("selected_language", null)
    }
}
