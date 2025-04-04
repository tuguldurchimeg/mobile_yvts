package com.example.mobile_yvts.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.mobile_yvts.ui.home.DisplayMode

val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsPreferences(private val context: Context) {

    companion object {
        private val DISPLAY_MODE_KEY = stringPreferencesKey("display_mode")
    }

    suspend fun saveDisplayMode(mode: DisplayMode) {
        context.dataStore.edit { preferences ->
            preferences[DISPLAY_MODE_KEY] = mode.name
        }
    }

    val displayModeFlow: Flow<DisplayMode> = context.dataStore.data
        .map { preferences ->
            val modeString = preferences[DISPLAY_MODE_KEY] ?: DisplayMode.FULL.name
            DisplayMode.valueOf(modeString)
        }
}
