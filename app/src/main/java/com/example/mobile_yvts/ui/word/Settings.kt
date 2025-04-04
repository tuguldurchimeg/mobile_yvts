package com.example.mobile_yvts.ui.word

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobile_yvts.ui.home.DisplayMode

@Composable
fun Settings(
    currentMode: DisplayMode,
    onModeChange: (DisplayMode) -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Select Display Mode", style = MaterialTheme.typography.titleLarge)

        RadioButtonGroup(
            title = "Show both",
            selected = currentMode == DisplayMode.FULL,
            onClick = { onModeChange(DisplayMode.FULL) }
        )
        RadioButtonGroup(
            title = "English only",
            selected = currentMode == DisplayMode.ONLY_ENGLISH,
            onClick = { onModeChange(DisplayMode.ONLY_ENGLISH) }
        )
        RadioButtonGroup(
            title = "Mongolian only",
            selected = currentMode == DisplayMode.ONLY_MONGOLIAN,
            onClick = { onModeChange(DisplayMode.ONLY_MONGOLIAN) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(onClick = onBack) {
            Text("Back")
        }
    }
}

@Composable
fun RadioButtonGroup(title: String, selected: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected, onClick = onClick)
        Text(text = title, modifier = Modifier.padding(start = 8.dp))
    }
}
